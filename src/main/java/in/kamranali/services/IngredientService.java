package in.kamranali.services;

import in.kamranali.commands.IngredientCommand;

public interface IngredientService {

	IngredientCommand findByRecipeIdAndIngredientId(Long recipeId, Long ingredientId);
	IngredientCommand saveIngredientCommand(IngredientCommand ingredientCommand);

}
