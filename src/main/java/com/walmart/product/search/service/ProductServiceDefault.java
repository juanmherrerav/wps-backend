package com.walmart.product.search.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.mongodb.client.FindIterable;
import com.walmart.product.search.business.Constants;
import com.walmart.product.search.business.Product;
import com.walmart.product.search.model.ProductModel;
import com.walmart.product.search.model.ProductQuery;
import com.walmart.product.search.util.VOUtils;
import org.bson.Document;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service(Constants.PRODUCT_IMPLEMENTATION_DEFAULT)
public class ProductServiceDefault implements ProductService {

    //private static Logger logger = LogManager.getLogger(ProductServiceDefault.class);

    private static final int MIN_QUERY_CHARACTERS_SIZE = 3;
    private static final double DISCOUNT_PERCENTAGE = 50d;
    private static final double PERCENT_100 = 100d;

    @Override
    public List<Product> search(String query) {

        ProductModel m = new ProductModel();
        ProductQuery productQuery = generateSearchQuery(query);
        FindIterable<Document> documents = m.searchProduct(productQuery);
        List<Product> productList = validateSalePrice(toProductList(documents),query);
        return productList;
    }

    private List<Product> validateSalePrice(List<Product> unCheckedProductList, String query) {
        List<Product> productListForSale = new ArrayList<>();
        if(VOUtils.isWordPalindrome(query)){
            for(Product product: unCheckedProductList){
                productListForSale.add(onSaleProduct(product));
            }
        }else{
            for(Product product: unCheckedProductList){
                productListForSale.add(normalPrizeProduct(product));
            }
        }
        return productListForSale;
    }

    private Product onSaleProduct(Product product) {
        product.setOnSalePrice( product.getPrice() * ( (PERCENT_100 - DISCOUNT_PERCENTAGE) / PERCENT_100) );
        System.out.println(product.getOnSalePrice()+"/"+product.getPrice() * ( (PERCENT_100 - DISCOUNT_PERCENTAGE) / PERCENT_100)  +"/"+(PERCENT_100 - DISCOUNT_PERCENTAGE) +"/"+( (PERCENT_100 - DISCOUNT_PERCENTAGE) / PERCENT_100) +"/"+product.getPrice() );
        return product;
    }

    private Product normalPrizeProduct(Product product) {
        product.setOnSalePrice(product.getPrice());
        return product;
    }

    private List<Product> toProductList(FindIterable<Document> documents) {
        List<Product> productList = new ArrayList<>();
        for (Document document: documents){
            try {
                productList.add(VOUtils.documentToProduct(document));
            } catch (JsonProcessingException e) {
                System.err.println("Could Not Convert Json to POJO" + e.getOriginalMessage());
            }
        }
        return productList;
    }

    private ProductQuery generateSearchQuery(String query) {
        ProductQuery productQuery = new ProductQuery();
        if (VOUtils.isLong(query)){
            productQuery.setId(Long.parseLong(query));
        }
        if (VOUtils.exists(query) && query.length() >= MIN_QUERY_CHARACTERS_SIZE){
            productQuery.setDescription(query);
            productQuery.setBrand(query);
        }
        return productQuery;
    }

}
