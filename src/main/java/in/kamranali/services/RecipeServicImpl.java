package in.kamranali.services;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.kamranali.domain.Recipe;
import in.kamranali.repositories.RecipeRepository;

@Service
public class RecipeServicImpl implements RecipeService {

	@Autowired
	private RecipeRepository recipeRepo;
	
	@Override
	public Set<Recipe> getRecipes() {
		Set<Recipe> recipeSet = new HashSet<>();
		recipeRepo.findAll().iterator().forEachRemaining(recipeSet::add);
		return recipeSet;
	}

}
