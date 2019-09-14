package com.example.bff.controller;


import com.example.bff.service.BFFService;
import com.netflix.discovery.EurekaClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
@RequestMapping("/bff")
public class BFFController{

    private Logger logger = LoggerFactory.getLogger(BFFController.class);

    @Autowired
    BFFService service;


    @RequestMapping(method = RequestMethod.POST , value ="/catalog")
    @ResponseBody
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<String> addProduct(@RequestBody Object product) {
        logger.debug("entered add() >>"+product);
        service.addProduct(product);
        return  new ResponseEntity<String>("",HttpStatus.CREATED);

    }


    @DeleteMapping("/catalog/delete/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<String> deleteProduct(@PathVariable long id) {
        logger.debug("entered delete() >>"+id);
        service.delete(id);
        return  new ResponseEntity<String>("Done with "+id,HttpStatus.OK);

    }

    @RequestMapping(method= RequestMethod.GET,value="catalog/getall" )
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    public Object[] getAllProducts() {
        logger.debug("entered getAll >>");
        return service.getAll();
    }
}
