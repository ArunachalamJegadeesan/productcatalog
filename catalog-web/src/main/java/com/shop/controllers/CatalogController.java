package com.shop.controllers;


import java.util.Map;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.shop.model.Product;
import com.shop.service.CatalogService;

@Controller
public class CatalogController {

	Logger logger = LoggerFactory.getLogger(CatalogController.class);
	
	
    private final CatalogService service;


    public CatalogController(CatalogService service) {
    	this.service=service;
    }
	
    @RequestMapping(value = "/", method = RequestMethod.GET)
	public String 	create(Map<String, Object> model){
		logger.debug("inside createProduct>>>>");
		model.put("productForm",new Product());
		return "createProduct";
	}


	@RequestMapping(value = "/catalogAdd", method = RequestMethod.POST)
	public String doCreate(@Valid @ModelAttribute("productForm") Product productForm,
			Map<String, Object> model,BindingResult bindingResult) {
		
		logger.debug("Entering addProduct..");
		
		if (bindingResult.hasErrors()) {
			logger.debug("Retuning back to  productpage");
			return "redirect:/createProduct";
		}
		
		service.create(productForm);						
				
		logger.debug("after save Product>>>>");
		return "redirect:/refresh";
	}

	@RequestMapping(value = "/delete", method = RequestMethod.GET)
	public String delete(@RequestParam Long id) {
		logger.debug("Entering delete..");
		service.delete(id);
		logger.debug("after delete Product>>>>");
		return "redirect:/refresh";
	}
	
	@RequestMapping(value = "/refresh", method = RequestMethod.GET)
	public String 	refresh(Map<String, Object> model){				
		logger.debug("inside refresh>>>>");
		Product[] products = service.retrieve();	
		logger.debug("retrived :"+products);
		model.put("productForm",new Product());
		model.put("products",products);
		return "createProduct";
	}
	
	
	
}