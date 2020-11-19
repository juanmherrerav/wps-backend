package com.walmart.product.search.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.walmart.product.search.business.Product;
import org.bson.Document;

public class VOUtils {
    public static boolean exists(String brand) {
        return null != brand && !brand.isEmpty();
    }

    public static boolean isLong(String strNum) {
        if (strNum == null) {
            return false;
        }
        try {
            double d = Long.parseLong(strNum);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }

    public static boolean isWordPalindrome(String word){
        StringBuffer buffer = new StringBuffer(word);
        buffer.reverse();
        String reverseWord = buffer.toString();
        return word.equals(reverseWord);
    }

    public static Product documentToProduct(Document document) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        return (Product) mapper.readValue(document.toJson(), Product.class);
    }
}
