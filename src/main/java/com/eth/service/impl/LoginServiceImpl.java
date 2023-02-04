package com.eth.service.impl;

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
import com.eth.vo.OperatorInfoVO;
import com.eth.vo.ResponseResult;
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
        OperatorInfoVO operatorInfoVO = new OperatorInfoVO();
        BeanUtils.copyProperties(operatorPo, operatorInfoVO);
        return new ResponseResult(HttpStatus.OK.value(), operatorInfoVO);
    }


    // @Transactional
    // @Override
    // public ResponseResult userRegist(LoginForm registUser) {
    //
    //     // 查看前端传输过来的密码是否符合要求
    //     // 由数字和字母组成，长度在6-15位
    //     String password = registUser.getPassword();
    //     if (StringUtil.isNullOrEmpty(password) || !(password.matches("^[0-9a-zA-Z]{6,15}$"))) {
    //         return new ResponseResult(HttpStatus.UNAUTHORIZED.value(), "注册失败,密码不符合规范！");
    //     }
    //
    //     // 用户名格式检查
    //     String email = registUser.getUsername();
    //     String match = "^\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*$";
    //     if (StringUtil.isNullOrEmpty(email) || email.length() > 40 || !(email.matches(match))) {
    //         return new ResponseResult(HttpStatus.UNAUTHORIZED.value(), "注册失败,用户名不符合规范！");
    //     }
    //     // 用户名是否已经注册
    //     if (LoginMapper.getLoginUser(email) != null) {
    //         return new ResponseResult(HttpStatus.UNAUTHORIZED.value(), "注册失败,该用户名已注册！");
    //     }
    //
    //     // 验证码检查
    //     String redisKey = "regist:" + email;
    //     String code = (String) redisCache.getCacheObject(redisKey);
    //     if (StringUtil.isNullOrEmpty(registUser.getCode()) || (!registUser.getCode().equals(code))) {
    //         return new ResponseResult(HttpStatus.UNAUTHORIZED.value(), "验证码不正确！");
    //     }
    //
    //     // 密码加密后再存储数据库
    //     String BCPassword = new BCryptPasswordEncoder().encode(registUser.getPassword());
    //
    //     // 将新用户分别插入两个数据表，这里得加事务了
    //     User newUser = new User();
    //     try {
    //         if (LoginMapper.addUser(newUser) != 0) {
    //             if (LoginMapper.addLoginUser(newUser.getOperatorId(),   BCPassword) != 0) {
    //                 return new ResponseResult(HttpStatus.OK.value(), "注册成功");
    //             }
    //         }
    //     } catch (RuntimeException e) {
    //         // 抛出异常回滚事务
    //         throw new MySQLException(e.toString(), new ResponseResult(ResultEnum.DB_ERROR));
    //     }
    //     return new ResponseResult(HttpStatus.UNAUTHORIZED.value(), "注册失败,新用户未插入数据表！");
    // }


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

    // @Override
    // public ResponseResult getRegistCode(String email) {
    //     // 用户名格式检查
    //     String match = "^\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*$";
    //     if (StringUtil.isNullOrEmpty(email) || email.length() > 40 || !(email.matches(match))) {
    //         return new ResponseResult(HttpStatus.UNAUTHORIZED.value(), "用户名不符合规范！");
    //     }
    //     // 用户名是否已经注册
    //     if (LoginMapper.getLoginUser(email) != null) {
    //         return new ResponseResult(HttpStatus.UNAUTHORIZED.value(), "该用户名已注册！");
    //     }
    //
    //     // 生成随机数验证码
    //     Random random = new Random();
    //     String result = "";
    //     for (int i = 0; i < 6; i++) {
    //         result += random.nextInt(10);
    //     }
    //
    //     // redis 保存用户验证用户名验证码 180秒过期
    //     redisCache.setCacheObject("regist:" + email, result, 180, TimeUnit.SECONDS);
    //
    //     // 发送邮件
    //     EmailUtils.sendEmail(email, result);
    //     return new ResponseResult(200, "发送成功，请查看用户名验证码");
    // }
}
