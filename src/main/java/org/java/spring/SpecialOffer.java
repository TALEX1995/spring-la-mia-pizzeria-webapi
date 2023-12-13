package org.java.spring;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class SpecialOffer {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	private LocalDate offerStart;
	private LocalDate offerEnd;
	private String title;
	
	public SpecialOffer() { }
	
	public SpecialOffer(LocalDate offerStart, LocalDate offerEnd, String title, Pizza pizza) {
		setOfferStart(offerStart);
		setOfferEnd(offerEnd);
		setTitle(title);
		setPizza(pizza);
	}

	public LocalDate getOfferStart() {
		return offerStart;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Pizza getPizza() {
		return pizza;
	}

	public void setPizza(Pizza pizza) {
		this.pizza = pizza;
	}

	public void setOfferStart(LocalDate offerStart) {
		this.offerStart = offerStart;
	}

	public LocalDate getOfferEnd() {
		return offerEnd;
	}

	public void setOfferEnd(LocalDate offerEnd) {
		this.offerEnd = offerEnd;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
	
	@JsonIgnore
	@ManyToOne
	@JoinColumn(nullable = false)
	private Pizza pizza;
	
}
