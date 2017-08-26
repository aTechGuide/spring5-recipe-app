package in.kamranali.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

import in.kamranali.commands.RecipeCommand;
import in.kamranali.exceptions.NotFoundException;
import in.kamranali.services.RecipeService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class RecipeController {

	private RecipeService recipeService;
	
	public RecipeController(RecipeService recipeService) {
		this.recipeService = recipeService;
	}

	@GetMapping("/recipe/{id}/show")
	public String getIndexPage(@PathVariable String id, Model model){
		
		model.addAttribute("recipe", recipeService.findById(new Long(id)));
		return "recipe/show";
	}
	
	@GetMapping("/recipe/new")
	public String newRecipe(Model model){
		
		model.addAttribute("recipe", new RecipeCommand());
		return "recipe/recipeform";
	}
	
	@GetMapping("/recipe/{id}/update")
	public String updateRecipe(@PathVariable String id, Model model){
		
		model.addAttribute("recipe", recipeService.findCommandById(Long.valueOf(id)));
		return "recipe/recipeform";
	}
	
	@PostMapping("recipe")
	public String saveOrUpdate(@ModelAttribute RecipeCommand command){
		
		RecipeCommand savedCommand = recipeService.saveRecipeCommand(command);
		return "redirect:/recipe/" + savedCommand.getId() + "/show";
	}
	
	@GetMapping("/recipe/{id}/delete")
	public String deleteById(@PathVariable String id){
		
		log.debug("Deleting id:" + id);
		recipeService.deleteByid(Long.valueOf(id));
		return "redirect:/";
	}
	
	@ResponseStatus(HttpStatus.NOT_FOUND)
	@ExceptionHandler(NotFoundException.class)
	public ModelAndView handleNotFound(){
		
		log.error("Handling Not found Exception:");
		
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("404error");
		return modelAndView;
	}
}
