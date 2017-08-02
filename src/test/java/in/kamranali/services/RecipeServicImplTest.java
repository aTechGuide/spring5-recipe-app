package in.kamranali.services;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import in.kamranali.domain.Recipe;
import in.kamranali.repositories.RecipeRepository;

public class RecipeServicImplTest {

	private RecipeServicImpl recipeService;
	
	@Mock
	private RecipeRepository recipeRepository;
	
	@Before
	public void setUp(){
		MockitoAnnotations.initMocks(this);
		recipeService = new RecipeServicImpl(recipeRepository);
	}
	
	@Test
	public void testGetRecipes() {
		
		Set<Recipe> recipeSet = Stream.of(new Recipe()).collect(Collectors.toCollection(HashSet::new));
				
		when(recipeRepository.findAll()).thenReturn(recipeSet);
		Set<Recipe> recipes = recipeService.getRecipes();
		
		assertEquals(recipes.size(), 1);
		verify(recipeRepository, times(1)).findAll();
	}

}
