package com.example.bff.service;


import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
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

    @Value("${catalog.service.uri}")
    private String catalogserviceURI;

    @HystrixCommand(fallbackMethod = "proxyAdd")
    public void addProduct(Object product) {
        template.postForLocation(catalogserviceURI,product);
    }

    @HystrixCommand(fallbackMethod = "proxyDelete")
    public void delete(long product) {
        logger.debug("Delete product");
        template.delete(catalogserviceURI+"/delete/"+product);
    }

    @HystrixCommand(fallbackMethod = "proxygetAll")
    public  Object[] getAll() {
               Object pro[]= template.getForObject(catalogserviceURI+"/getall",Object[].class);
               return pro;
    }
    public void proxyDelete(long product) {
        logger.debug("Catalog Service Down  try later... .");
    }
    public Object[] proxygetAll() {
        logger.debug("Catalog Service Down  try later... .");
        Object temp[]=null;
        return temp;
    }
    public void proxyAdd(Object product) {
        logger.debug("Catalog Service Down  try later... .");
    }
    }
