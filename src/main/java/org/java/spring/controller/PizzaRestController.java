package org.java.spring.controller;

import java.util.ArrayList;
import java.util.List;

import org.java.spring.Pizza;
import org.java.spring.db.serv.PizzaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
@RequestMapping("api/pizzas")
public class PizzaRestController {

	@Autowired
	private PizzaService pizzaService;
	
	@GetMapping
	public ResponseEntity<List<Pizza>> getIndex(
			@RequestParam(value= "nameParam", required = false) String nameParam){
		
		List<Pizza> pizzas = pizzaService.findAll();
		
		List<Pizza> filteredPizzas = new ArrayList<>();

		if (nameParam != null) {
				
			for (Pizza pizza : pizzas) {
				if (pizza.getName().toLowerCase().contains(nameParam.toLowerCase())) {
					filteredPizzas.add(pizza);
		        }
		    }
		} else {
	        filteredPizzas = pizzas;
	    }
		
		return new ResponseEntity<>(filteredPizzas, HttpStatus.OK);
	}
	
	@GetMapping("{id}")
	public ResponseEntity<Pizza> getPizza(
			@PathVariable int id) {
		
		Pizza pizza = pizzaService.findById(id);
		
		if(pizza == null) return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		
		return new ResponseEntity<>(pizza, HttpStatus.OK);
	}
	
	@PostMapping
	public ResponseEntity<Pizza> create(
			@RequestBody Pizza newPizza){
		
		pizzaService.save(newPizza);
		
		return new ResponseEntity<>(newPizza, HttpStatus.OK);
	}
	
	@PutMapping("{id}")
	public ResponseEntity<Pizza> update(
			@PathVariable int id,
			@RequestBody Pizza updatedPizza){
		
		Pizza pizza = pizzaService.findById(id);
		if(updatedPizza.getName() != null) {
			pizza.setName(updatedPizza.getName());
		}
		
		if(updatedPizza.getDescription() != null) {
			pizza.setDescription(updatedPizza.getDescription());
		}
		
		
		if(updatedPizza.getPrice() != 0.0f) {
			 pizza.setPrice(updatedPizza.getPrice());
		}
	    	
		pizzaService.save(pizza);
		
		return new ResponseEntity<>(pizza, HttpStatus.OK);
	}
	
	@DeleteMapping("{id}")
	public ResponseEntity<Pizza> delete(
			@PathVariable int id){
		
		Pizza pizza = pizzaService.findById(id);
		
		pizzaService.delete(pizza);
		
		return new ResponseEntity<>(pizza, HttpStatus.OK);
	}
	
	
}
