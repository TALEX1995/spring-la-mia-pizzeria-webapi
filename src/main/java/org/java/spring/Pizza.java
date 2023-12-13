package org.java.spring;

import java.util.Arrays;
import java.util.List;
import java.util.Set;

import org.hibernate.validator.constraints.Length;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

@Entity
public class Pizza {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@NotBlank(message = "Il nome è obbligatorio")
	@Length(min = 3, max = 255, message = "Il nome deve essere più lungo di 3 caratteri e massimo 255")
	@Column(nullable = false)
	private String name;
	
	@NotBlank(message = "La descrizione è obbligatorio")
	@Column(columnDefinition = "TEXT", nullable = false)
	private String description;
	
	@Column(columnDefinition = "TEXT")
	private String photo;
	
	@NotNull(message = "Il prezzo è obbligatorio")
	@Positive(message = "Il prezzo deve essere superiore a zero")
	@Column(nullable = false)
	private float price;
	

	@ManyToMany
	private List<Ingredient> ingredients;
	
	public Pizza() {}
	
	public Pizza(String name, String description, String photo, float price, Ingredient...ingredients) {
		setName(name);
		setDescription(description);
		setPhoto(photo);
		setPrice(price);
		setIngredients(ingredients);
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}
	
	@OneToMany(mappedBy = "pizza")
	private Set<SpecialOffer> specialOffers;


	public Set<SpecialOffer> getSpecialOffers() {
		return specialOffers;
	}

	public void setSpecialOffers(Set<SpecialOffer> specialOffers) {
		this.specialOffers = specialOffers;
	}

	public List<Ingredient> getIngredients() {
		return ingredients;
	}

	public void setIngredients(List<Ingredient> ingredients) {
		this.ingredients = ingredients;
	}
	
	public void setIngredients(Ingredient...ingredients) {
		setIngredients(Arrays.asList(ingredients));
	}
	
	


	
	
}
