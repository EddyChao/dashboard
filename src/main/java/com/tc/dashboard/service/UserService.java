package com.tc.dashboard.service;

import com.tc.dashboard.bean.User;
import com.tc.dashboard.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class UserService implements UserDetailsService {


    @Autowired
    UserMapper userMapper;

    @Override
    public UserDetails loadUserByUsername(String user) throws UsernameNotFoundException {
        User userLogin = userMapper.findUserByName(user);

        if (userLogin != null) {
//            Collection<? extends GrantedAuthority> authorities = userLogin.getAuthorities();
//            for (GrantedAuthority authority : authorities) {
//                System.out.println(authority);
//            }
            return userLogin;
        } else {
            throw new UsernameNotFoundException(user + "not exist");
        }
    }
}
