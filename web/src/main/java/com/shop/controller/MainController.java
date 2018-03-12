package com.shop.controller;

import com.shop.server.model.Comment;
import com.shop.server.model.Product;
import com.shop.server.service.CommentService;
import com.shop.server.service.ProductService;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
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
    @Autowired
    CommentService commentService;



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

    @RequestMapping(value = "/product/{productId}", method = RequestMethod.GET)
    public String product(@PathVariable String productId , ModelMap modelMap) {
        try {
            Long id = Long.valueOf(productId);
            Product product=productService.getProductById(id);
            modelMap.addAttribute("product", product);
            modelMap.addAttribute("comments",productService.getCommentByProduct(product.getProductId()));
            return "product";
        }
        catch (Exception e){
            log.debug("***ERROR***"  + e.getMessage());
            return "main";
        }
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
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(product);
        }
    }
    @RequestMapping(value = "/addComment",method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Comment> saveComment(@RequestBody Comment comment){
        try{
            log.debug("Добавляю комментарий");
            Long productId = comment.getProduct().getProductId();
            log.debug("Ид продукта" +productId);
            commentService.saveComment(comment,productId);
            log.debug("Добавил");
            return ResponseEntity.ok(comment);
        }catch (Exception e){
            log.error(e.getMessage(),e);
            log.debug("ERROR");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(comment);
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
