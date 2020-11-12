package com.tc.dashboard.config;

import org.springframework.boot.web.error.ErrorAttributeOptions;
import org.springframework.boot.web.servlet.error.DefaultErrorAttributes;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.WebRequest;

import java.util.Map;

@Component
public class MyErrorAttributes extends DefaultErrorAttributes {

    @Override
    public Map<String, Object> getErrorAttributes(WebRequest webRequest, ErrorAttributeOptions options) {
//        return super.getErrorAttributes(webRequest, options);
        Map<String, Object> map = super.getErrorAttributes(webRequest, options);
        map.put("company", "tencent");
        Map<String, Object> ext = (Map<String, Object>) webRequest.getAttribute("ext", 0);
        System.out.println(ext);
        map.put("ext", ext);
        return map;
    }
}
