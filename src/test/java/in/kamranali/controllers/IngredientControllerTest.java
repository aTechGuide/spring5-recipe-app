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

import in.kamranali.commands.IngredientCommand;
import in.kamranali.commands.RecipeCommand;
import in.kamranali.services.IngredientService;
import in.kamranali.services.RecipeService;

public class IngredientControllerTest {

	@Mock
	RecipeService recipeService;
	@Mock
	IngredientService ingredientService;
	
	IngredientController controller;
	
	MockMvc mockMVC;
	
	@Before
	public void setUp(){
		
		MockitoAnnotations.initMocks(this);
		controller = new IngredientController(recipeService, ingredientService);
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
	
	@Test
	public void testShowIngredient() throws Exception {
		
		when(ingredientService.findByRecipeIdAndIngredientId(Mockito.anyLong(), Mockito.anyLong())).thenReturn(new IngredientCommand());
		
		mockMVC.perform(get("/recipe/1/ingredient/2/show"))
		.andExpect(status().isOk())
		.andExpect(view().name("recipe/ingredient/show"))
		.andExpect(model().attributeExists("ingredient"));
		
		verify(ingredientService).findByRecipeIdAndIngredientId(Mockito.anyLong(), Mockito.anyLong());
	}

}
