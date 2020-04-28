package com.example.web.service;


import com.example.web.model.Product;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class WebService {
	
	Logger logger = LoggerFactory.getLogger(WebService.class);

	@Autowired
	private  RestTemplate template;

	@Value("${bff.uri}")
	private  String bffURL;

	public void create(Product product){
		logger.debug("catalogservice create invoked.." + bffURL );
		if(product!=null)
		template.postForLocation( bffURL, product);
	}

	public Product[] retrieve(){
		logger.debug("catalogservice retrive invoked.." + bffURL);
		 Product[] products  =  template.getForObject( bffURL+"/getall", Product[].class);
		 return products;
	}

	public void delete(long id){
		logger.debug("catalogservice delete  invoked: "+ bffURL );
			template.delete( bffURL+"/delete/"+id);
	}



}
