package com.ebook.admin.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.convert.ReadingConverter;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ebook.admin.domain.Book;
import com.ebook.admin.service.BookService;

@Controller
@RequestMapping("/book")
public class BookController {
	@Autowired
	private BookService bookService;
	
	@GetMapping("/add")
	public String addBookGet(Model model) {
		Book book = new Book();
		model.addAttribute("book", book);
		return "addBook";
	}
	
	@PostMapping("/add")
	public String addBookPost(@ModelAttribute("book") Book book) {
		System.out.println(book);
		bookService.save(book);
		return "redirect:bookList";
	}
	
	@GetMapping("/bookList")
	public String bookList(Model model) {
		List<Book> books = bookService.findAll();
		model.addAttribute("books", books);
		return "bookList";
	}
	
	@GetMapping("/bookInfo")
	public String bookInfo(@RequestParam Long id,Model model) {
		Book book = bookService.findOne(id);
		model.addAttribute("book", book);
		return "bookInfo";
	}
	
	@GetMapping("/update")
	public String updateBookGet(@RequestParam Long id,Model model) {
		Book book = bookService.findOne(id);
		model.addAttribute("book", book);
		return "updateBook";
	}
	@PostMapping("/update")
	public String updateBookPost(@ModelAttribute("book") Book book) {
		bookService.save(book);
		return "redirect:bookList";
	}
	
	@GetMapping("/delete")
	public String deleteBook(@RequestParam Long id) {
		bookService.removeOne(id);
		return "redirect:bookList";
	}
	

}
