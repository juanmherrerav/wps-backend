package com.walmart.product.search.service;

public interface ProductServiceFactory {
    ProductService get(String serviceTypeName);
}
