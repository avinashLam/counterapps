package com.efficy.counterapp.controller;

import com.efficy.counterapp.dto.Product;
import com.efficy.counterapp.service.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("v1/api/product")
public class ProductController {
    private static final Logger logger = LoggerFactory.getLogger(ProductController.class);
    @Autowired
    private ProductService productService;
    @PostMapping
    public ResponseEntity<Product> createProduct(@RequestBody Product product){
        logger.debug("Product creation request: {}",product);
        final var created=productService.createProduct(product);
        logger.debug("Created Product{}",created);
        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }
    @GetMapping("/get")
    public ResponseEntity<List<Product>> getProducts(){
        final var products = productService.getProducts();
        logger.debug("Products found : {}",products);
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<Product> getProduct(@PathVariable Integer id) {
        logger.debug("Product get with id {}");
        final var product = productService.getProduct(id);
        if (product == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        logger.info("product found : {}", product);
        return new ResponseEntity<>(product, HttpStatus.OK);
    }

    @PutMapping("/increment/{id}")
    public ResponseEntity<Product> incrementProduct(@PathVariable Integer id) {
        var product = productService.getProduct(id);
        if (product == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        product = productService.incrementProduct(product);
        return new ResponseEntity<>(product, HttpStatus.OK);
    }





}
