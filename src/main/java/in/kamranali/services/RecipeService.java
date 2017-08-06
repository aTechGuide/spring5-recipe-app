package in.kamranali.services;

import java.util.Set;

import in.kamranali.domain.Recipe;

public interface RecipeService {

	Set<Recipe> getRecipes();
	Recipe findByID(Long l);
}
