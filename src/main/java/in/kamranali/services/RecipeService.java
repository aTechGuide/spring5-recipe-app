package in.kamranali.services;

import java.util.Set;

import in.kamranali.commands.RecipeCommand;
import in.kamranali.domain.Recipe;

public interface RecipeService {

	Set<Recipe> getRecipes();
	Recipe findById(Long l);
	
	RecipeCommand saveRecipeCommand(RecipeCommand command);
	RecipeCommand findCommandById(Long anyLong);

	void deleteByid(Long id);
}
