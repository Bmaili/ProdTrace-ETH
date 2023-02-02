package com.eth.security;

import com.eth.pojo.LoginUserPo;
import com.eth.mapper.LoginMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class JwtUserDetailServiceImpl implements UserDetailsService {
    @Autowired
    LoginMapper loginMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // 通过用户名登陆用户
        LoginUserPo user = loginMapper.getLoginUser(username);
        if (Objects.isNull(user)) {
            throw new RuntimeException("此用户不存在！");
        }
        return new JwtUser(user.getUsername(), user.getPassword());
    }
}
