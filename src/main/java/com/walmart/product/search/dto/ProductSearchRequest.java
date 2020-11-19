package com.walmart.product.search.dto;

import java.util.StringJoiner;

public class ProductSearchRequest {

    private String query;

    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = query;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", ProductSearchRequest.class.getSimpleName() + "[", "]")
                .add("query='" + query + "'")
                .toString();
    }
}
