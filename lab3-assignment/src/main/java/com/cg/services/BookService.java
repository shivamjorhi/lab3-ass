package com.cg.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.daos.BookDAO;
import com.cg.entities.Book;
import com.cg.exceptions.ApplicationException;

@Service
public class BookService {
	
	@Autowired private BookDAO dao;
	
	
	public void save(Book book) {
			dao.create(book);
	}
	
	public List<Book> getAll(){
		List<Book> books = dao.getAll();
		if(books == null || books.isEmpty()) {
			throw new ApplicationException("No books available yet!");
		}
		return books;
	}
}
