package com.walmart.product.search.service;

import com.walmart.product.search.business.Product;
import org.junit.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class ProductServiceDefaultTest {

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    public void testGiven_Preconditions_When_StateUnderTest_Then_NotDiscount() {
        ProductServiceDefault productServiceDefault = new ProductServiceDefault();
        List<Product> searchResult = productServiceDefault.search("123");
        for(Product product: searchResult){
            System.out.println(product.toString());
        }
        Product first = searchResult.get(0);
        assertThat("123 id search is not palindrome, therefore does not have Discount",first.getPrice()==first.getOnSalePrice(),is(true));
    }

    @Test
    public void testGiven_Preconditions_When_StateUnderTest_Then_Discount() {
        ProductServiceDefault productServiceDefault = new ProductServiceDefault();
        List<Product> searchResult = productServiceDefault.search("121");
        for(Product product: searchResult){
            System.out.println(product.toString());
        }
        Product first = searchResult.get(0);
        assertThat("121 id search is palindrome, therefore has Discount",first.getPrice()>first.getOnSalePrice(),is(true));
    }


    @Test
    public void testGiven_Preconditions_When_StateUnderTest_Then_ExpectedBehavior2() {
        ProductServiceDefault productServiceDefault = new ProductServiceDefault();
        List<Product> searchResult = productServiceDefault.search("bmfwuq");
        for(Product product: searchResult){
            System.out.println(product.toString());
        }
        assertThat("bmfwuq search Should Bring 11 articules ",searchResult.size(),is(11));

    }
}