package com.shop.controller;

import com.shop.server.model.Product;
import com.shop.server.service.ProductService;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@Log4j
@Controller
public class MainController {

    @Autowired
    ProductService productService;



    @RequestMapping(value = {"/","/main"}, method = RequestMethod.GET)
    public String main(ModelMap model) {
        log.debug("render main");
        model.addAttribute("products",productService.getAllProducts());
        return "main";
    }

    @RequestMapping(value="/logout", method = RequestMethod.GET)
    public String logoutPage (HttpServletRequest request, HttpServletResponse response) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null){
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }
        return "redirect:/login?logout";
    }


    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public ModelAndView login() {
        log.debug("render login");
        ModelAndView model=new ModelAndView();
        return model;
    }

    @RequestMapping(value = "/product", method = RequestMethod.GET)
    public ModelAndView product(@RequestParam Long id) {
        ModelAndView model=new ModelAndView();
            model.addObject("product", productService.getProductById(id));
            return model;
    }



    @RequestMapping(value = "/addProduct",method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Product> saveProduct(@RequestBody Product product){
        try {
            log.debug("*****SAVE*****");
            productService.saveProduct(product);
            log.debug("Save complete");
            return ResponseEntity.ok(product);
        }catch (Exception e){
            log.error(e.getMessage(),e);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }
    @RequestMapping(value = "/deleteProduct",method = RequestMethod.DELETE,produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> deleteProduct(@RequestParam Long id) {
        try {
            log.debug("Get Product Id" + id);
            log.debug("*****DELETE*****");
            productService.deleteProductById(id);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            log.error("********ERROR********");
            log.error(e.getMessage(), e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
