package in.kamranali.controllers;

import static org.junit.Assert.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import in.kamranali.commands.RecipeCommand;
import in.kamranali.services.RecipeService;

public class IngredientControllerTest {

	@Mock
	RecipeService recipeService;
	
	IngredientController controller;
	
	MockMvc mockMVC;
	
	@Before
	public void setUp(){
		
		MockitoAnnotations.initMocks(this);
		controller = new IngredientController(recipeService);
		mockMVC = MockMvcBuilders.standaloneSetup(controller).build();
		
	}
	@Test
	public void testListIngredients() throws Exception {
		
		when(recipeService.findCommandById(Mockito.anyLong())).thenReturn(new RecipeCommand());
		
		mockMVC.perform(get("/recipe/1/ingredients"))
		.andExpect(status().isOk())
		.andExpect(view().name("recipe/ingredient/list"))
		.andExpect(model().attributeExists("recipe"));
		
		verify(recipeService).findCommandById(Mockito.anyLong());
	}

}
