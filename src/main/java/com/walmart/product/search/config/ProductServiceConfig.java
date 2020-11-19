package com.walmart.product.search.config;

import com.walmart.product.search.service.ProductServiceFactory;
import org.springframework.beans.factory.config.ServiceLocatorFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ProductServiceConfig {

	@Bean
	public ServiceLocatorFactoryBean slfbForProductServiceFactory() {
		ServiceLocatorFactoryBean slfb = new ServiceLocatorFactoryBean();
		slfb.setServiceLocatorInterface(ProductServiceFactory.class);
		return slfb;
	}

}
