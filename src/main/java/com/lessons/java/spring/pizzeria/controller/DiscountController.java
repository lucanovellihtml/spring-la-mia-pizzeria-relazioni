package com.lessons.java.spring.pizzeria.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.lessons.java.spring.pizzeria.model.Discount;
import com.lessons.java.spring.pizzeria.model.Pizza;
import com.lessons.java.spring.pizzeria.service.PizzaService;

import jakarta.validation.Valid;



@Controller
@RequestMapping("/discounts")
public class DiscountController {

	
	//SERVICE
	//REPOSITORY CAMPO CON AUTOWIRED CON D.I, DIRE ALL'APPLICATIVO DI INSTANZIARE IN AUTOMATICO IL SERVICE
	@Autowired
	private DiscountService service;
	
	
	/**
	 * 
	 * @return LA LISTA DI TUTTE LE OFFERTE;
	 */
	@GetMapping
	public String index(  Model model) {
		
		//PRENDO I DATI DA MOSTRARE A "/discount/index":
		List<Discount> listDiscount;
		
		listDiscount = service.findAllDiscount();

		//INSERISCO I DATI DELLA LISTA NEL MODEL;
		model.addAttribute("pizze", listDiscount);
		
		return "/discount/index";
		
	}
	
}
