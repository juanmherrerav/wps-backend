package com.walmart.product.search.service;

import com.walmart.product.search.business.Product;

import java.util.List;

public interface ProductService {
    public List<Product> search(String query) ;
}
