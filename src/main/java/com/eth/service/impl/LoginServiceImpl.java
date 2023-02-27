package com.eth.service.impl;

import com.eth.enums.ResultEnum;
import com.eth.pojo.LoginUserPo;
import com.eth.pojo.OperatorPo;
import com.eth.form.LoginForm;
import com.eth.mapper.LoginMapper;
import com.eth.mapper.OperatorMapper;
import com.eth.security.JwtUser;
import com.eth.security.JwtUserDetailServiceImpl;
import com.eth.service.LoginService;
import com.eth.utils.JwtUtils;
import com.eth.utils.RedisCache;
import com.eth.vo.OperatorInfoVo;
import com.eth.vo.ResponseResult;
import io.netty.util.internal.StringUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;

@Service
public class LoginServiceImpl implements LoginService {
    @Autowired
    LoginMapper loginMapper;

    @Autowired
    OperatorMapper operatorMapper;

    @Autowired
    private RedisCache redisCache;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUserDetailServiceImpl jwtUserDetailService;

    @Autowired
    private HttpServletRequest httpServletRequest;

    @Override
    public ResponseResult login(LoginForm loginForm) {

        // 验证码检查
        String verifyKey = "captchaImage:" + loginForm.getUuid();
        String captcha = redisCache.getCacheObject(verifyKey);
        redisCache.deleteObject(verifyKey);
        if (captcha == null || (!captcha.equals(loginForm.getCode()))) {
            return new ResponseResult(HttpStatus.UNAUTHORIZED.value(), "验证码错误！");
        }

        // 通过电子用户名登陆用户
        LoginUserPo user = loginMapper.getLoginUser(loginForm.getUsername());
        if (user == null) {
            return new ResponseResult(HttpStatus.UNAUTHORIZED.value(), "登陆失败,未注册用户！");
        }

        JwtUser userDetails = (JwtUser) jwtUserDetailService.loadUserByUsername(loginForm.getUsername());
        if (!new BCryptPasswordEncoder().matches(loginForm.getPassword(), userDetails.getPassword())) {
            return new ResponseResult(HttpStatus.UNAUTHORIZED.value(), "登陆失败,密码错误！");
        }

        Authentication token = new UsernamePasswordAuthenticationToken(loginForm.getUsername(), loginForm.getPassword(), userDetails.getAuthorities());
        Authentication authentication = authenticationManager.authenticate(token);

        SecurityContextHolder.getContext().setAuthentication(authentication);

        // 将token设置到map里返回给客户端
        String userId = user.getUsername().toString();
        String jwt = JwtUtils.createJWT(userId);
        HashMap<String, String> map = new HashMap<>();
        map.put("Authorization", jwt);

        // 将token设置到redis，将user设置为redis的值，注意把密码改掉以防泄露
        // user.setToken(jwt);
        userDetails.setPassword("");
        userDetails.setToken(jwt);
        redisCache.setCacheObject("login:" + userId, userDetails);

        return new ResponseResult(HttpStatus.OK.value(), "登陆成功", map);
    }

    @Override
    public ResponseResult getUserInfo() {
        String userId = (String) httpServletRequest.getAttribute("userId");
        OperatorPo operatorPo = operatorMapper.selectById(userId);
        OperatorInfoVo operatorInfoVO = new OperatorInfoVo();
        BeanUtils.copyProperties(operatorPo, operatorInfoVO);
        return new ResponseResult(HttpStatus.OK.value(), operatorInfoVO);
    }

    @Override
    public ResponseResult logout() {

        // filter从redis里的token提取出用户id后，将其设置在"userId"里
        String userId = (String) httpServletRequest.getAttribute("userId");

        //删除redis中的值
        if (redisCache.deleteObject("login:" + userId)) {
            return new ResponseResult(HttpStatus.OK.value(), "登出成功", "OK");
        }
        return new ResponseResult(HttpStatus.INTERNAL_SERVER_ERROR.value(), "登出失败！", "false");
    }

    @Override
    public ResponseResult updatePassword(String oldPassword,String newPassword) {
        // 查看前端传输过来的密码是否符合要求
        // 由数字或字母组成，长度在6-15位
        if (StringUtil.isNullOrEmpty(newPassword) || !(newPassword.matches("^[0-9a-zA-Z]{6,15}$"))) {
            return new ResponseResult(ResultEnum.PASS_WRONGFUL);
        }

        String userId = (String) httpServletRequest.getAttribute("userId");
        LoginUserPo user = loginMapper.getLoginUser(userId);
        if (!new BCryptPasswordEncoder().matches(oldPassword, user.getPassword())) {
            return new ResponseResult(ResultEnum.PASS_DIFF_ERROR);
        }

        String encode = new BCryptPasswordEncoder().encode(newPassword);
        loginMapper.updateLoginUserPassword(userId, encode);
        return new ResponseResult(ResultEnum.SUCCESS_OF_UPDATE);
    }
}
