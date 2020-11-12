package com.tc.dashboard.config;

import com.tc.dashboard.exception.UserNotExistException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class MyErrorController  {

    @ExceptionHandler(UserNotExistException.class)
    public String handleException(Exception e, HttpServletRequest request){
        Map<String, Object> map = new HashMap<>();

        request.setAttribute("javax.servlet.error.status_code", 500);
        map.put("msg", "出现错误了呀！！！");
        map.put("code", "500");
        request.setAttribute("ext", map);
        return "forward:/error";
    }
}
