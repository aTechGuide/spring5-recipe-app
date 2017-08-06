package in.kamranali.services;

 import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.springframework.stereotype.Service;

import in.kamranali.domain.Recipe;
import in.kamranali.repositories.RecipeRepository;

@Service
public class RecipeServicImpl implements RecipeService {

	private RecipeRepository recipeRepository;

	public RecipeServicImpl(RecipeRepository recipeRepository) {
		this.recipeRepository = recipeRepository;
	}

	@Override
	public Set<Recipe> getRecipes() {
		Set<Recipe> recipeSet = new HashSet<>();
		recipeRepository.findAll().iterator().forEachRemaining(recipeSet::add);
		return recipeSet;
	}

	@Override
	public Recipe findByID(Long l) {

		Optional<Recipe> recipeOptional = recipeRepository.findById(l);
		
		if(!recipeOptional.isPresent())
			throw new RuntimeException("Recipe Not Found !!");
		
		return recipeOptional.get();
	}

}
