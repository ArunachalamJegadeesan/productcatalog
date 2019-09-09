package com.example.bff.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.EurekaClient;
import org.springframework.web.util.UriComponentsBuilder;

@Component
public class BFFService {


    private Logger logger = LoggerFactory.getLogger(BFFService.class);
    @Autowired
    private RestTemplate template;

    private EurekaClient discoveryClient;

    @Autowired
    public BFFService(EurekaClient discoveryClient){
        this.discoveryClient = discoveryClient;

    }


    public void addProduct(Object product) {
        template.postForLocation(catalogURL(),product);
    }

    public  Object[] getAll() {
               Object pro[]= template.getForObject(catalogURL()+"/getall",Object[].class);
               return pro;
    }

    private String catalogURL() {
        InstanceInfo instance = discoveryClient.getNextServerFromEureka("CATALOGSERVICE", false);
        logger.debug("instanceID: {}", instance.getId());
        StringBuilder url = new StringBuilder(instance.getHomePageUrl())
                                        .append("/catalog");
        logger.debug("bff service homePageUrl: {}", url.toString());
        return url.toString();
    }

    }
