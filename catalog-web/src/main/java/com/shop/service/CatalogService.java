package com.shop.service;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.cloud.client.ServiceInstance;
import com.netflix.appinfo.InstanceInfo;
import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.EurekaClient;


import com.shop.model.Product;

import java.util.List;

@Service
public class CatalogService {
	
	Logger logger = LoggerFactory.getLogger(CatalogService.class);

	@Autowired
	private  RestTemplate template;


	@Autowired
	private EurekaClient discoveryClient;


	@Value("${catalog.bff.name}")
	private  String bffAppName;

	public void create(Product product){	
		logger.debug("catalogservice create invoked.." );
		if(product!=null)
		template.postForLocation( bffURL(), product);
	}

	public Product[] retrieve(){
		logger.debug("catalogservice retrive invoked.." );
		 Product[] products  =  template.getForObject( bffURL()+"/getall", Product[].class);
		 return products;
	}

	public void delete(long id){
		logger.debug("catalogservice delete  invoked" );
			template.delete( bffURL()+"/delete/"+id);
	}

	private String bffURL() {
		InstanceInfo instance = discoveryClient.getNextServerFromEureka(bffAppName, false);
		logger.debug("instanceid: {}", instance.getId());

		String bffURL = instance.getHomePageUrl();
		logger.debug("bff service homeurl: {}", bffURL);

		return bffURL+"/bff/catalog";
	}

}
