package org.java.spring.controller;


import java.util.List;

import org.java.spring.Ingredient;
import org.java.spring.Pizza;
import org.java.spring.db.serv.IngredientService;
import org.java.spring.db.serv.PizzaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.validation.Valid;

@Controller
public class MainController {

	@Autowired
	private PizzaService pizzaService;
	
	@Autowired
	private IngredientService ingredientService;
	
	@GetMapping("/")
	public String home() {
		return "home";
	}

	@GetMapping("/pizzas")
	public String pizzaIndex(Model model, @RequestParam(required=false) String pizzaName) {
		
		
		List<Pizza> pizzas = pizzaName == null  
				? pizzaService.findAll()
				: pizzaService.findByName(pizzaName);
		
		model.addAttribute("pizzas", pizzas);
		model.addAttribute("pizzaName", pizzaName);
		
		return "pizzas";
	}
	
	@GetMapping("/pizzas/create")
	public String pizzaCreate(Model model) {
		
		Pizza pizza = new Pizza();
		List<Ingredient> ingredients = ingredientService.findAll();
		
		model.addAttribute("ingredients", ingredients);
		
		model.addAttribute("pizza", pizza);
		return "pizza-form";
	}
	
	@PostMapping("/pizzas/create")
	public String pizzaStore(Model model,
			@Valid @ModelAttribute Pizza pizza,
			BindingResult bindingResult) {
		
		if (bindingResult.hasErrors()) {
			
			System.out.println(bindingResult);
			model.addAttribute("pizza", pizza);
			return "pizza-form";
		}
		
		pizzaService.save(pizza);
		
		return "redirect:/pizzas";
	}
	
	
	@GetMapping("/pizzas/{id}")
	public String pizzaDetail(Model model, @PathVariable int id) {
		
		Pizza pizza = pizzaService.findById(id);
		
		
		model.addAttribute("pizza", pizza);
		model.addAttribute("offers", pizza.getSpecialOffers());
		
		return "pizza-detail";
	}
	
	@GetMapping("/pizzas/edit/{id}")
	public String pizzaEdit(Model model, @PathVariable int id) {
		
		Pizza pizza = pizzaService.findById(id);
		List<Ingredient> ingredients = ingredientService.findAll();
		
		model.addAttribute("ingredients", ingredients);
		model.addAttribute("pizza", pizza);
		
		return "pizza-form";
	}
	
	@PostMapping("/pizzas/edit/{id}")
	public String pizzaUpdate(Model model,
			@Valid @ModelAttribute Pizza pizza,
			BindingResult bindingResult) {
		
		if (bindingResult.hasErrors()) {
			
			System.out.println(bindingResult);
			model.addAttribute("pizza", pizza);
			return "pizza-create";
		}
		
		pizzaService.save(pizza);
		
		return "redirect:/pizzas";
	}
	
	@PostMapping("/pizzas/delete/{id}")
	public String pizzaUpdate(Model model, @PathVariable int id) {
		
		Pizza pizza = pizzaService.findById(id);
		
		pizzaService.delete(pizza);
		
		return "redirect:/pizzas";
	}
}
