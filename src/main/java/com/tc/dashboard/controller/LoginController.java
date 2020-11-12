package com.tc.dashboard.controller;

import com.tc.dashboard.mapper.LoginMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

@Controller
@SessionAttributes("user")
//如果不加此注解那么拦截器中将获取不到user的值
//因为在一次请求中，如果controller处理之后，ModelAndView的数据将被放到request中，本次请求结束之后，request中的数据将失效
//而thymeleaf的${user.username}是从request中取user，所以在页面跳转之后，其他页面的${user.username}将抛出空指针异常
//所以有两种解决方式，第一种，在发请求的时候，在controller中将session的user获取出来，然后将其放入ModelAndView中
//第二种，在页面使用thymeleaf模板引擎${#session.getAttribute('user').username}获取出来

public class LoginController {

    @Autowired
    LoginMapper loginMapper;

//    @PostMapping("/login")
//    public ModelAndView Login(User user, ModelAndView mv){
//        User forLogin = loginMapper.findForLogin(user);
//        if (forLogin != null) {
//            mv.addObject("user", user).setViewName("dashboard");
//        } else {
//            mv.addObject("loginError", "用户名或密码错误").setViewName("login");
//        }
//        return mv;
//    }

    @GetMapping("/userlogin")
    public String login(){
//        return "redirect:login.html";
        return "login";
    }

//    @GetMapping("/loginout")
//    public String loginOut(HttpServletRequest request){
//        Object user = request.getSession().getAttribute("user");
//        System.out.println("退出：" + user);
//        request.removeAttribute("user");
//        request.removeAttribute("noUser");
//        request.removeAttribute("loginError");
//        return "login";
//    }
}
