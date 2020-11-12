package com.tc.dashboard.config;

import com.tc.dashboard.service.UserService;
import com.tc.dashboard.utils.Md5PassWordEncoder;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * 一、看下内部原理
 *
 * 简化后的认证过程分为7步:
 *
 * 1.用户访问网站，打开了一个链接(origin url)。
 *
 * 2.请求发送给服务器，服务器判断用户请求了受保护的资源。
 *
 * 3.由于用户没有登录，服务器重定向到登录页面
 *
 * 4.填写表单，点击登录
 *
 * 5.浏览器将用户名密码以表单形式发送给服务器
 *
 * 6.服务器验证用户名密码。成功，进入到下一步。否则要求用户重新认证（第三步）
 *
 * 7.服务器对用户拥有的权限（角色）判定: 有权限，重定向到origin url; 权限不足，返回状态码403("forbidden").
 *
 * 从第3步，我们可以知道，用户的请求被中断了。
 *
 * 用户登录成功后（第7步），会被重定向到origin url，spring security通过使用缓存的request，使得被中断的请求能够继续执行。
 */
@EnableWebSecurity
public class MySecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //super.configure(http);
        //定制请求的授权规则
        http.csrf().disable()
                .authorizeRequests()
                .antMatchers("/dashboard").hasAnyRole("管理员", "普通用户")
                .antMatchers("/admin/**").hasRole("管理员")
                .antMatchers("/emp/**").hasRole("管理员");
        //开启自动配置的登陆功能，效果，如果没有登陆，没有权限就会来到登陆页面
        http.formLogin().usernameParameter("user").passwordParameter("pwd")
                .loginPage("/userlogin")
                .successForwardUrl("/dashboard");
        //1、/login来到登陆页
        //2、重定向到/login?error表示登陆失败
        //3、更多详细规定
        //4、默认post形式的 /login代表处理登陆
        //5、一但定制loginPage；那么 loginPage的post请求就是登陆

        //开启自动配置的注销功能。
        http.logout().logoutSuccessUrl("/userlogin");//注销成功以后来到首页
        //1、访问 /logout 表示用户注销，清空session
        //2、注销成功会返回 /login?logout 页面；
        //开启记住我功能
        http.rememberMe().rememberMeParameter("remember-me");
        //登陆成功以后，将cookie发给浏览器保存，以后访问页面带上这个cookie，只要通过检查就可以免登录
        //点击注销会删除cookie

    }
    @Bean
    public PasswordEncoder Md5PasswordEncoder(){
        return new Md5PassWordEncoder();
    }
    //定义认证规则
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        //super.configure(auth);
        auth.userDetailsService(userDetailsService()).passwordEncoder(Md5PasswordEncoder());

    }
    @Bean
    public UserDetailsService userDetailsService() {
        return new UserService();
    }
}
