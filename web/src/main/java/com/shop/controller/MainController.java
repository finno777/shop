package com.shop.controller;

import com.shop.server.server.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Map;

@Controller
public class MainController {

    @Autowired
    ProductService productService;


    @RequestMapping("/main")
    public String main(Map<String, Object> model) {
        model.put("products",productService.getAllProducts());
        return "main";
    }
}
