package com.shop.service;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.EurekaClient;

import com.shop.model.Product;

@Service
public class CatalogService {
	
	public CatalogService(){};
	
	Logger logger = LoggerFactory.getLogger(CatalogService.class);
	private EurekaClient discoveryClient;
	
	@Autowired
	private  RestTemplate template;
	
	@Value("${bff.endpoint.url}")
	private  String bffURL;

	@Autowired
	public CatalogService(EurekaClient discoveryClient){
		this.discoveryClient = discoveryClient;
	}
	
	public void create(Product product){	
		logger.debug("catalogservice create invoked.." );
		if(product!=null)
		template.postForLocation(bffURL(), product);
	}

	public Product[] retrieve(){
		logger.debug("catalogservice retrive invoked.." );
		 Product[] products  =  template.getForObject(bffURL()+"/getall", Product[].class);
		return products;
	}

	public void delete(long id){
		logger.debug("catalogservice delete  invoked" );
			template.delete(bffURL()+"/delete/"+id);
	}

	private String bffURL() {
		InstanceInfo instance = discoveryClient.getNextServerFromEureka("BFF", false);
		logger.debug("instanceid: {}", instance.getId());

		String bffURL = instance.getHomePageUrl();
		logger.debug("bff service homeurl: {}", bffURL);

		return bffURL+"/bff/catalog";
	}
	

}
