package com.example.bff.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;

@Service
public class BFFService {

    private Logger logger = LoggerFactory.getLogger(BFFService.class);

    @Autowired
    private RestTemplate template;

    @Value("${catalogservice.uri}")
    private String catalogserviceURI;


    public void addProduct(Object product) {
        template.postForLocation(catalogserviceURI,product);
        logger.debug("added product :" +product.toString());
    }


    public void delete(long product) {
        template.delete(catalogserviceURI+"/delete/"+product);
        logger.debug("Deleted product :" +product);
    }


    public  Object[] getAll() {
               Object pro[]= template.getForObject(catalogserviceURI+"/getall",Object[].class);
               logger.debug("Return Product List:");
               return pro;
    }

    }
