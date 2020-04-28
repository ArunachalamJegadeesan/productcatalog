package com.example.bff.controller;


import com.example.bff.service.BFFService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@Api(
        value = "bff",
        produces = "application/json")
@RestController
@RequestMapping("/bff")
public class BFFController{

    private Logger logger = LoggerFactory.getLogger(BFFController.class);

    @Autowired
    BFFService service;


    @RequestMapping(method = RequestMethod.POST , value ="/catalog")
    @ResponseBody
    @ResponseStatus(HttpStatus.CREATED)
    @ApiOperation(value = "adds the given product to bff-catalog", notes = "Adds products to bff-catalog")
    @ApiResponses(value=
            {@ApiResponse(code = 500,message = "Fields are with validation errors"),
                    @ApiResponse(code = 400, message = "Error occurred while processing request")}
    )
    public ResponseEntity<String> addProduct(@RequestBody Object product) {
        logger.debug("entered add() >>"+product);
        service.addProduct(product);
        return  new ResponseEntity<String>("",HttpStatus.CREATED);

    }


    @DeleteMapping("/catalog/delete/{id}")
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "list products in bff-catalog", notes = "lists  all product in the bff-catalog with state, regoin code and availablity status")
    public ResponseEntity<String> deleteProduct(@PathVariable long id) {
        logger.debug("entered delete() >>"+id);
        service.delete(id);
        return  new ResponseEntity<String>("Done with "+id,HttpStatus.OK);

    }

    @RequestMapping(method= RequestMethod.GET,value="catalog/getall" )
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "delete the given product from bff-catalog", notes = "Delete  products from bff-catalog")
    @ApiResponses(value=
            {@ApiResponse(code = 500,message = "Fields are with validation errors"),
                    @ApiResponse(code = 400, message = "Error occurred while processing request")}
    )
    public Object[] getAllProducts() {
        logger.debug("entered getAll >>");
        return service.getAll();
    }
}
