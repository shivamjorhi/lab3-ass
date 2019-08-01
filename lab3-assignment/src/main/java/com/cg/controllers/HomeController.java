package com.cg.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cg.entities.Book;
import com.cg.exceptions.ApplicationException;
import com.cg.services.BookService;

@Controller
@RequestMapping("/")
public class HomeController {

	@GetMapping
	public String viewHome(Model model) {
		System.out.println("Home controller");
		return "home";
	}
	
	@Autowired private BookService service;
	
	@PostMapping
	public String newSave(Model model, @ModelAttribute Book book,BindingResult result) {
		if(result.hasErrors()) {
			model.addAttribute("msg","Invalid form input");
		}
		try {
			service.save(book);
			model.addAttribute("msg","Book "+book.getTitle()+" added successfuly!");
			model.addAttribute("book",new Book()); //Empty the form fields on success
		}catch(ApplicationException ex) {
			System.out.println(ex.getMessage());
			model.addAttribute("msg",ex.getMessage());
		}
		return "redirect:/viewall";
	}
	
	@GetMapping("/viewall")
	public String findAll(Model model) {
		String message = null;
		try {
		List<Book> books = service.getAll();
		message = books.size()+" records found!";
		model.addAttribute("books",books);
		}catch(ApplicationException ex) {
			message = "No records found!";
		}
		model.addAttribute("msg",message);
		return "view-all";
	}
}
