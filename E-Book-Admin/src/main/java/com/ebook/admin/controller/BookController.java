package com.ebook.admin.controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
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
import org.springframework.web.multipart.MultipartFile;

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
		
		bookService.save(book);
		MultipartFile bookImage = book.getBookImage();

		try {
			byte[] bytes = bookImage.getBytes();
			String name = book.getId() + ".png";
			BufferedOutputStream stream = new BufferedOutputStream(
					new FileOutputStream(new File("src/main/resources/static/image/book/" + name)));
			stream.write(bytes);
			stream.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
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
