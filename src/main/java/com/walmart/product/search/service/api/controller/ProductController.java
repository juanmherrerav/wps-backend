package com.walmart.product.search.service.api.controller;


import com.walmart.platform.exception.BadRequestException;
import com.walmart.platform.reponse.BaseResponse;
import com.walmart.platform.reponse.DataResponse;
import com.walmart.product.search.business.Product;
import com.walmart.product.search.config.ConfigProperties;
import com.walmart.product.search.service.ProductServiceFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.Calendar;
import java.util.List;

@RestController
@RequestMapping("/api/v1/products")
public class ProductController {
    @Autowired
    ProductServiceFactory productServiceFactory;

    @Autowired
    protected ConfigProperties configProperties;


    @CrossOrigin
    @RequestMapping(value = "/search", method = RequestMethod.GET)
    public ResponseEntity<BaseResponse> searchProduct(@RequestParam("query") String query){
        //checkRequest(bindingResult);

        List<Product> productList =productServiceFactory.get(configProperties.getDocumentImplementation())
                .search(query);
        return  this.createSuccessResponse(productList, "SuccessFull Search");

    }

    protected void checkRequest(BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new BadRequestException(bindingResult);
        }
    }

    protected ResponseEntity<BaseResponse> createSuccessResponse(Object resultData, String message) {
        return this.createOkResponse(resultData, message, HttpStatus.OK);
    }

    private ResponseEntity<BaseResponse> createOkResponse(Object resultData, String message, HttpStatus httpStatus) {
        DataResponse dataResponse = new DataResponse();
        dataResponse.setMessage(message != null ? message : "Operation successful");
        dataResponse.setStatus(httpStatus.value());
        dataResponse.setTimestamp(Calendar.getInstance().getTimeInMillis());
        if (resultData != null) {
            dataResponse.setReturnData(resultData);
        }

        return new ResponseEntity(dataResponse, httpStatus);
    }
}
