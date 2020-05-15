
package com.example.web.controllers;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import com.example.web.model.Product;
import com.example.web.service.WebService;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import static org.mockito.Mockito.when;
@RunWith(SpringRunner.class)
@WebMvcTest(controllers=WebController.class , secure = false)
public class CatalogControllerTest {
	
	@MockBean
    private WebService service;

	@Autowired
	private MockMvc mockMvc;

	@Test
	public void WebRoot_ShouldShowCreateProductPageWithEmptyValues() throws Exception 
	{
		this.mockMvc.perform(get("/")).andDo(print()).andExpect(status().isOk())
		.andExpect(forwardedUrl("/WEB-INF/jsp/createProduct.jsp"))
         .andExpect(model().attribute("productForm", hasProperty("productName", isEmptyOrNullString())))
         .andExpect(model().attribute("productForm", hasProperty("stateCode", isEmptyOrNullString())))
         .andExpect(model().attribute("productForm", hasProperty("regionCode", isEmptyOrNullString())))
         .andExpect(model().attribute("productForm", hasProperty("available", isEmptyOrNullString())))
         .andExpect(model().attribute("productForm", hasProperty("usoc", isEmptyOrNullString())))
		.andExpect(view().name("createProduct"));
	}
	
	@Test 
	public void refreshPage_ShouldShowAllProductsAdded() throws Exception{
		Product[] products= {
		 new Product("iPhone8","i5365","Dallas","TX","Y",1),
		 new Product("iPhone5S","i5368","Dallas","TX","Y",2)};
		when(service.retrieve()).thenReturn(products);
		this.mockMvc.perform(get("/refresh")).andDo(print()).andExpect(status().isOk())
		.andExpect(model().attribute("products", products))
		.andExpect(view().name("createProduct"));
	}
}
