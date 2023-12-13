package org.java.spring;

import java.time.LocalDate;
import java.util.List;

import org.java.spring.auth.conf.AuthConf;
import org.java.spring.auth.db.pojo.Role;
import org.java.spring.auth.db.pojo.User;
import org.java.spring.auth.db.serv.RoleService;
import org.java.spring.auth.db.serv.UserService;
import org.java.spring.db.serv.IngredientService;
import org.java.spring.db.serv.PizzaService;
import org.java.spring.db.serv.SpecialOfferService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringLaMiaPizzeriaCrudApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(SpringLaMiaPizzeriaCrudApplication.class, args);
	}

	@Autowired
	private PizzaService pizzaService;
	
	@Autowired
	private SpecialOfferService specialOfferService;
	
	@Autowired
	private IngredientService ingredientService;
	
	@Autowired
	private RoleService roleService;
	
	@Autowired
	private UserService userService;
	
	@Override
	public void run(String... args) throws Exception {
		
		Ingredient i1 = new Ingredient("Pomodoro");
		Ingredient i2 = new Ingredient("Mozzarella");
		Ingredient i3 = new Ingredient("Basilico");
		Ingredient i4 = new Ingredient("Prosciutto cotto");
		ingredientService.save(i1);
		ingredientService.save(i2);
		ingredientService.save(i3);
		ingredientService.save(i4);
		
		pizzaService.save(new Pizza("Pizza Margherita", "Una normale pizza", "https://upload.wikimedia.org/wikipedia/commons/c/c8/Pizza_Margherita_stu_spivack.jpg", 5.99f, i1, i2));
		pizzaService.save(new Pizza("Pizza Crostino", "Una pizza con prosciutto e mozzarella", "https://hallopizza.it/wp-content/uploads/2015/03/IMG_0109.jpg", 6.99f, i1, i4));
		pizzaService.save(new Pizza("Pizza Boscaiola", "Una pizza con funghi", "https://media-cdn.tripadvisor.com/media/photo-s/1c/67/4a/a9/pizza-boscaiola.jpg", 7.99f, i2));
		pizzaService.save(new Pizza("Pizza Rossa", "Una pizza con pomodoro", "https://media-cdn.tripadvisor.com/media/photo-s/1c/67/4a/a9/pizza-boscaiola.jpg", 7.99f, i1, i3));
		pizzaService.save(new Pizza("Pizza alici", "Una pizza con alici", "https://media-cdn.tripadvisor.com/media/photo-s/1c/67/4a/a9/pizza-boscaiola.jpg", 7.99f, i1));
		pizzaService.save(new Pizza("Pizza Wurstel e patatine", "Una pizza wurstel e patatine", "https://i0.wp.com/www.piccolericette.net/piccolericette/wp-content/uploads/2017/11/3244_Pizza.jpg?resize=895%2C616&ssl=1", 7.99f));
	
		List<Pizza> pizzas = pizzaService.findAll();
		
		specialOfferService.save(new SpecialOffer(LocalDate.now(), LocalDate.now().plusDays(3), "Speciale Offerta Natale", pizzas.get(0)));
		specialOfferService.save(new SpecialOffer(LocalDate.now().plusDays(5), LocalDate.now().plusDays(8), "Speciale Offerta 20 anni Attività", pizzas.get(1)));
		
		Role roleUser = new Role("USER");
		Role roleAdmin = new Role("ADMIN");
		
		roleService.save(roleUser);
		roleService.save(roleAdmin);
		
		String pass = AuthConf.passwordEncoder().encode("password");
		
		User alexUser = new User("alexUser", pass, roleUser);
		User alexAdmin = new User("alexAdmin", pass, roleAdmin);
		
		userService.save(alexUser);
		userService.save(alexAdmin);
	}
	
	

}
