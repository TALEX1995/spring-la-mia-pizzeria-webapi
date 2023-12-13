package org.java.spring.controller;

import java.util.List;

import org.java.spring.Ingredient;
import org.java.spring.Pizza;
import org.java.spring.db.serv.IngredientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class IngredientController {
	
	@Autowired
	private IngredientService ingredientService;

	
	@GetMapping("/ingredients")
	public String ingredientsIndex(Model model) {
		
		List<Ingredient> ingredients = ingredientService.findAll();
		
		model.addAttribute("ingredients", ingredients);
		
		return "ingredients";
	}
	
	@GetMapping("/ingredients/create")
	public String createIngredient(Model model) {
		
		Ingredient ingredient = new Ingredient();
		
		model.addAttribute("ingredient", ingredient);
		
		return "ingredient-form";
	}
	
	@PostMapping("/ingredients/create")
	public String storeIngredient(Model model, @ModelAttribute Ingredient ingredient) {
		
		ingredientService.save(ingredient);
		
		return "redirect:/ingredients";
	}
	
	@PostMapping("/ingredients/delete/{id}")
	public String deleteIngredient(@PathVariable int id) {
		
		Ingredient i = ingredientService.findById(id);
		
		List<Pizza> pizzas = i.getPizzas();
		
		if(pizzas.size() > 0) {
			for(Pizza pizza : pizzas) {
				pizza.getIngredients().remove(i);
			}
		}
	
		ingredientService.delete(i);
		
		return "redirect:/ingredients";
	}
}
