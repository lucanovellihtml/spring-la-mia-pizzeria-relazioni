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

import com.lessons.java.spring.pizzeria.model.Pizza;
import com.lessons.java.spring.pizzeria.repo.PizzaRepository;

import jakarta.validation.Valid;



@Controller
@RequestMapping("/pizze")
public class PizzaController {

	//REPOSITORY CAMPO CON AUTOWIRED CON D.I
	@Autowired
	private PizzaRepository repo;
	
	
	/**
	 * 
	 * @return LA LISTA DI TUTTE LE PIZZE;
	 * @return LA PIZZA SINGOLA;
	 */
	@GetMapping
	public String index(@RequestParam(name = "name", required = false) String name,  Model model) {
		
		//PRENDO I DATI DA MOSTRARE A "/pizze/index":
		List<Pizza> listPizze;
		
		if( name != null && !name.isEmpty() )
			listPizze = repo.findByName(name);
		else
			listPizze = repo.findAll();

		//INSERISCO I DATI DELLA LISTA NEL MODEL;
		model.addAttribute("pizze", listPizze);
		
		return "/pizza/index";
		
	}
	
	
	/**
	 * 
	 * @return LA SINGOLA PIZZA CON ID;
	 */
	@GetMapping("/show/{id}")
	public String indexSinglePizza(@PathVariable("id") int id , Model model) {
		
		//INSERISCO I DATI DELLA PIZZA NEL MODEL;
		model.addAttribute("pizza", repo.findById(id).get());
		
		return "/pizza/single-pizza";
		
	}
	
	
	/**
	 * CREAZIONE DELLA SINGOLA PIZZA
	 * @return FORM PER LA CREAZIONE DELLA SINGOLA PIZZA;
	 */
	@GetMapping("/create")
	public String create(Model model) {
		
		//INSERISCO L'OGGETTO PIZZA VUOTO;
		//QUESTO PER PERMETTERE DI RICHIMARE SEMPRE LA PAGINA ANCHE SENZA DATI;
		model.addAttribute("pizza", new Pizza());
		
		return "/pizza/form-create-pizza";
		
	}
	
	
	/**
	 * MEMORIZZAZIONE DELLA SINGOLA PIZZA CREATA NEL FORM;
	 * @return SE I DATI SONO SBAGLIATI, VIENE RIPROPOSTO IL FORM DA RICOMPILARE;
	 * @return UNA VOLTA SALVATI I DATI, VIENE RESTITUITA LA LISTA DELLE PIZZE;
	 */
	@PostMapping("/create")
	public String store(@Valid @ModelAttribute("pizza") Pizza formPizza, BindingResult bindingResult, Model model) {
		
		//CONTROLLO SE I CAMPI COMPILATI SONO SBAGLIATI;
		//SE CI SONO ERRORI, VIENE RESTITUITA LA PAGINA DEL FORM DA RICOMPILARE;
		//IN CASO CONTRARIO, I DATI VENGONO SALVATI SUL DB;
		if(bindingResult.hasErrors())
			return "/pizza/form-create-pizza";
		
		repo.save(formPizza);
		
		return "redirect:/pizze";
		
	}
	
	
	/**
	 * VIENE MOSTRATO IL FORM CON I DATI DELLA PIZZA CHE SI VUOLE MODIFICARE;
	 * @return I DATI DELLA PIZZA RICHIESTA; 
	 * 
	 */
	@GetMapping("/edit/{id}")
	public String edit(@PathVariable("id") int id , Model model) {
		
		//INSERISCO I DATI DELLA PIZZA NEL MODEL;
		model.addAttribute("pizza", repo.findById(id).get());
		
		return "/pizza/form-edit-pizza";
		
	}
	
	
	/**
	 * MEMORIZZAZIONE DELLA SINGOLA PIZZA MODIFICATA NEL FORM;
	 * @return SE I DATI SONO SBAGLIATI, VIENE RIPROPOSTO IL FORM DA RICOMPILARE;
	 * @return UNA VOLTA SALVATI I DATI, VIENE RESTITUITA LA LISTA DELLE PIZZE;
	 */
	@PostMapping("/edit/{id}")
	public String update(@Valid @ModelAttribute("pizza") Pizza formPizza, BindingResult bindingResult, Model model) {
		
		//CONTROLLO SE I CAMPI COMPILATI SONO SBAGLIATI;
		//SE CI SONO ERRORI, VIENE RESTITUITA LA PAGINA DEL FORM DA RICOMPILARE;
		//IN CASO CONTRARIO, I DATI VENGONO SALVATI SUL DB;
		if(bindingResult.hasErrors())
			return "/pizza/form-edit-pizza";
		
		repo.save(formPizza);
		
		return "redirect:/pizze";
		
	}
	
	
	/**
	 * ELIMINATO IL RECORD DAL REPOSITORY;
	 * @return LA LISTA AGGIORNATA; 
	 * 
	 */
	@PostMapping("/delete/{id}")
	public String delete(@PathVariable("id") int id, RedirectAttributes attributes) {
		
		//ELIMINO I DATI DELLA PIZZA DAL REPOSITORY;
		repo.deleteById(id);
		
		attributes.addFlashAttribute("successMessage", "Pizza deleted...");
		
		return "redirect:/pizze";
		
	}
	
}
