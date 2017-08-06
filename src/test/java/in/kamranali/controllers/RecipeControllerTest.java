package in.kamranali.controllers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.ui.Model;

import in.kamranali.services.RecipeService;

public class RecipeControllerTest {
	
	@Mock
	private RecipeService recipeService;
	@Mock
	private Model model;

	private RecipeController recipeController;

	@Before
	public void init() {
		MockitoAnnotations.initMocks(this);
		recipeController = new RecipeController(recipeService);
	}
	
	@Test
	// We are using a mocked servlet context (Mocked Dispatcher servlet) to test MVC controllers
	public void testMockMVC() throws Exception {
		MockMvc mockMvc = MockMvcBuilders.standaloneSetup(recipeController).build();
		
		mockMvc.perform(get("/recipe/show/1"))
		.andExpect(status().isOk())
		.andExpect(view().name("recipe/show"));
	}

}
