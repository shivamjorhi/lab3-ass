package com.cg.daos;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Repository;

import com.cg.entities.Book;

@Repository
public class BookDAO {
	
	
private List<Book> books = new ArrayList<>();
	
	@PostConstruct
	public void init() {
		books.add(new Book(101, "Let Us C", "Kanetkar"));
		books.add(new Book(102,"OOPs using C++","Balaguruswami"));
	}

	public void create(Book book) {
		books.add(book);
	}
	
	public List<Book> getAll(){
		return books;
	}
}
