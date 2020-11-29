package com.ebook.admin.repository;

import org.springframework.data.repository.CrudRepository;

import com.ebook.admin.domain.Book;

public interface BookRepository extends CrudRepository<Book, Long>{

}
