package com.walmart.product.search.service;

import com.walmart.product.search.business.Constants;
import com.walmart.product.search.business.Product;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service(Constants.PRODUCT_IMPLEMENTATION_DUMMY)
public class ProductServiceDummy implements ProductService {
    @Override
    public List<Product> search(String query) {
        List<Product> productList = new ArrayList<>();
        Product product = getProduct("https://www.lider.cl/catalogo/images/bedRoomIcon.svg", 1, "Demo Brand1", "Demo Product1", 123);
        Product product2 = getProduct("https://www.lider.cl/catalogo/images/toysIcon.svg", 2, "Demo Brand2", "Demo Product2", 124);
        Product product3 = getProduct("https://www.lider.cl/catalogo/images/toysIcon.svg", 3, "Demo Brand3", "Demo Product3", 125);
        productList.add(product);
        productList.add(product2);
        productList.add(product3);

        return productList;
    }

    private Product getProduct(String s, int id, String demo_brand, String demo_product, int price) {
        Product product = new Product();
        product.setId(id);
        product.setBrand(demo_brand);
        product.setDescription(demo_product);
        product.setImage(s);
        product.setPrice(price);
        return product;
    }
}
