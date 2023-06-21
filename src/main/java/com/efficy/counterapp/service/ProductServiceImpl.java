package com.efficy.counterapp.service;

import com.efficy.counterapp.dto.Product;
import com.efficy.counterapp.exception.InValidProductException;
import org.springframework.stereotype.Service;

import java.awt.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {
    private static Map<Integer,Product> productsMap=new ConcurrentHashMap<>();

    @Override
    public List<Product> getProducts(){
        return productsMap.values().stream().collect(Collectors.toList());
    }

    @Override
    public Product getProduct(final Integer id){
        return productsMap.get(id);
    }
    @Override
    public Product createProduct(Product product){
        if(product.getId()<=0){
            throw new InValidProductException("Product Id Should be Grater than 0");
        }
        addProductToMap(product);
        return product;
    }

    @Override
    public Product incrementProduct(Product product) {
        synchronized (product){
            product.setValue(product.getValue()+1);
        }
        return product;
    }

    private void addProductToMap(final Product product){
        final var productId=product.getId();
        if(productsMap.containsKey(productId)){
            throw new InValidProductException("Counter already exists");
        }
        productsMap.put(productId,product);
    }
}
