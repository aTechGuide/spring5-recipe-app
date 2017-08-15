package in.kamranali.controllers;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.util.HashSet;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import in.kamranali.commands.IngredientCommand;
import in.kamranali.commands.RecipeCommand;
import in.kamranali.services.IngredientService;
import in.kamranali.services.RecipeService;
import in.kamranali.services.UnitOfMeasureService;

public class IngredientControllerTest {

	@Mock
	RecipeService recipeService;
	@Mock
	IngredientService ingredientService;
	@Mock
	UnitOfMeasureService unitOfMeasureService;
	
	IngredientController controller;
	
	MockMvc mockMVC;
	
	@Before
	public void setUp(){
		
		MockitoAnnotations.initMocks(this);
		controller = new IngredientController(recipeService, ingredientService, unitOfMeasureService);
		mockMVC = MockMvcBuilders.standaloneSetup(controller).build();
		
	}
	@Test
	public void testListIngredients() throws Exception {
		
		when(recipeService.findCommandById(Mockito.anyLong())).thenReturn(new RecipeCommand());
		
		mockMVC.perform(get("/recipe/1/ingredients"))
		.andExpect(status().isOk())
		.andExpect(view().name("recipe/ingredient/list"))
		.andExpect(model().attributeExists("recipe"));
		
	}
	
	@Test
	public void testShowIngredient() throws Exception {
		
		when(ingredientService.findByRecipeIdAndIngredientId(Mockito.anyLong(), Mockito.anyLong())).thenReturn(new IngredientCommand());
		
		mockMVC.perform(get("/recipe/1/ingredient/2/show"))
		.andExpect(status().isOk())
		.andExpect(view().name("recipe/ingredient/show"))
		.andExpect(model().attributeExists("ingredient"));
		
	}
	
	@Test
	public void testNewIngredientForm() throws Exception {
		
		when(recipeService.findCommandById(Mockito.anyLong())).thenReturn(new RecipeCommand());
		when(unitOfMeasureService.listAllUoms()).thenReturn(new HashSet<>());
		
		mockMVC.perform(get("/recipe/1/ingredient/new"))
		.andExpect(status().isOk())
		.andExpect(view().name("recipe/ingredient/ingredientform"))
		.andExpect(model().attributeExists("ingredient"))
		.andExpect(model().attributeExists("uomList"));
		
	}
	
	@Test
	public void testUpdateIngredientForm() throws Exception {
		
		when(ingredientService.findByRecipeIdAndIngredientId(Mockito.anyLong(), Mockito.anyLong())).thenReturn(new IngredientCommand());
		when(unitOfMeasureService.listAllUoms()).thenReturn(new HashSet<>());
		
		mockMVC.perform(get("/recipe/1/ingredient/2/update"))
		.andExpect(status().isOk())
		.andExpect(view().name("recipe/ingredient/ingredientform"))
		.andExpect(model().attributeExists("ingredient"))
		.andExpect(model().attributeExists("uomList"));
		
	}
	
	@Test
	public void testSaveOrUpdate() throws Exception {
		
		IngredientCommand command = new IngredientCommand();
		command.setId(3L);
		command.setRecipeId(2L);
		
		when(ingredientService.saveIngredientCommand(Mockito.any())).thenReturn(command );
		
		mockMVC.perform(post("/recipe/2/ingredient")
				.contentType(MediaType.APPLICATION_FORM_URLENCODED)
				.param("id", "")
				.param("description", "Some String"))
		.andExpect(status().is3xxRedirection())
		.andExpect(view().name("redirect:/recipe/2/ingredient/3/show"));
		
	}
	
	@Test
	public void testDeleteIngredient() throws Exception {
		
		mockMVC.perform(get("/recipe/1/ingredient/2/delete"))
		.andExpect(status().is3xxRedirection())
		.andExpect(view().name("redirect:/recipe/1/ingredients"));
		
		verify(ingredientService, times(1)).deleteById(Mockito.anyLong(), Mockito.anyLong());
	}
}
