package com.tc.dashboard.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ProductsController {


    @GetMapping("/products/list")
    public String products(){
        return "products/product";
    }
}
