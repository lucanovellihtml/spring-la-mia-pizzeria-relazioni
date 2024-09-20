package com.lessons.java.spring.pizzeria.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.lessons.java.spring.pizzeria.model.Ingredient;
import com.lessons.java.spring.pizzeria.service.IngredientService;

@Controller
@RequestMapping("/ingredients")
public class IngredientController {
	
	//SERVICE
	//REPOSITORY CAMPO CON AUTOWIRED CON D.I, DIRE ALL'APPLICATIVO DI INSTANZIARE IN AUTOMATICO IL SERVICE
	@Autowired
	private IngredientService service;
	
	
	/**
	 * @return LA LISTA DI TUTTI GLI INGREDIENTI;
	 */
	@GetMapping
	public String index(Model model) {
		
		//PRENDO I DATI DA MOSTRARE A "/ingredient/index":
		List<Ingredient> listIngredients;
		
		listIngredients = service.findAllIngredients();

		//INSERISCO I DATI DELLA LISTA NEL MODEL;
		model.addAttribute("ingredients", listIngredients);
		
		return "/ingredient/index";
		
	}

}
