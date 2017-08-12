package in.kamranali.services;

import java.util.Optional;

import org.springframework.stereotype.Component;

import in.kamranali.commands.IngredientCommand;
import in.kamranali.converters.IngredientToIngredientCommand;
import in.kamranali.domain.Recipe;
import in.kamranali.repositories.RecipeRepository;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class IngredientServiceImpl implements IngredientService {
	
	private final IngredientToIngredientCommand ingredientToIngredientCommand;
	private final RecipeRepository recipeRepository;
	
	public IngredientServiceImpl(IngredientToIngredientCommand ingredientToIngredientCommand,
			RecipeRepository recipeRepository) {
		this.ingredientToIngredientCommand = ingredientToIngredientCommand;
		this.recipeRepository = recipeRepository;
	}

	@Override
	public IngredientCommand findByRecipeIdAndIngredientId(Long recipeId, Long ingredientId) {
		
		Optional<Recipe> recipeOptional = recipeRepository.findById(recipeId);
		
		if(! recipeOptional.isPresent()){
			log.error("Recipe ID not found ID:" + recipeId);
		}
		
		Optional<IngredientCommand> ingredientCommandOptional = recipeOptional.get().getIngredients().stream()
		.filter(ingredien -> ingredien.getId().equals(ingredientId))
		.map(ingredient -> ingredientToIngredientCommand.convert(ingredient))
		.findFirst();
		
		if(!ingredientCommandOptional.isPresent()){
			log.error("Ingredient ID not found ID:" + ingredientId);
		}
		
		return ingredientCommandOptional.get();
	}

}
