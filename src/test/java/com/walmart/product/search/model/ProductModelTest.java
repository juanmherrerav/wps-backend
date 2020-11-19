package com.walmart.product.search.model;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.walmart.product.search.ProductSearchApplication;
import com.walmart.product.search.business.Product;
import org.junit.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = ProductSearchApplication.class)
@SpringBootTest(classes=ProductSearchApplication.class)
public class ProductModelTest {


    @Test
    public void testGiven_Preconditions_When_StateUnderTest_Then_ExpectedBehavior() {
        ProductModel m = new ProductModel();
        ProductQuery productQuery = new ProductQuery();
        productQuery.setId(1);
        productQuery.setDescription("hqhoy");
        System.out.println(m.searchProduct(productQuery).first());
        System.out.println(m.searchProduct(productQuery).first().toJson());
    }

    @Test
    public void testGiven_Preconditions_When_StateUnderTest_Then_ExpectedBehavior2() throws JsonProcessingException {

        String json = "{\"_id\": {\"$oid\": \"5fb58313dfcd05cca3fab8bd\"}, \"id\": 1, \"brand\": \"ooy eqrceli\", \"description\": \"rlñlw brhrka\", \"image\": \"www.lider.cl/catalogo/images/whiteLineIcon.svg\", \"price\": 498724}";
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        Product readValue = mapper.readValue(json, Product.class);
        System.out.println("readValue = " + readValue.toString());
    }

    @BeforeEach
    void setUp(){

    }

    @AfterEach
    void tearDown() {
    }
}