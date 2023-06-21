package com.efficy.counterapp.service;

import com.efficy.counterapp.dto.Product;

import java.util.List;

public interface  ProductService {

     List<Product> getProducts();
     Product getProduct(Integer id);
     Product createProduct(Product product);
     Product incrementProduct(Product product);

}
