package com.tc.dashboard.utils;

import org.springframework.security.crypto.password.PasswordEncoder;

public class Md5PassWordEncoder implements PasswordEncoder {
    @Override
    public String encode(CharSequence rawPassword) {
        return MD5Utils.md5((String) rawPassword);
    }

    @Override
    public boolean matches(CharSequence rawPassword, String encodedPassword) {
        return encodedPassword.equals((MD5Utils.md5((String) rawPassword)));
    }
}
