package in.kamranali.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import in.kamranali.commands.RecipeCommand;
import in.kamranali.services.RecipeService;

@Controller
public class RecipeController {

	private RecipeService recipeService;
	
	public RecipeController(RecipeService recipeService) {
		this.recipeService = recipeService;
	}

	@RequestMapping("/recipe/show/{id}")
	public String getIndexPage(@PathVariable String id, Model model){
		
		model.addAttribute("recipe", recipeService.findByID(new Long(id)));
		return "recipe/show";
	}
	
	@RequestMapping("/recipe/new")
	public String newRecipe(Model model){
		
		model.addAttribute("recipe", new RecipeCommand());
		return "recipe/recipeform";
	}
	
	@PostMapping
	@RequestMapping("recipe")
	public String saveOrUpdate(@ModelAttribute RecipeCommand command){
		
		RecipeCommand savedCommand = recipeService.saveRecipeCommand(command);
		return "redirect:/recipe/show/" + savedCommand.getId();
	}
}
