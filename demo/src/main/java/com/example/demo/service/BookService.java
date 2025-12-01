package com.example.demo.service;

import com.example.demo.model.Book;
import java.util.List;

public interface BookService {

    List<Book> getAll();

    Book create(Book b);

    Book getOne(Long id);

    Book update(Long id, Book book);

    void deleteOne(Long id);

    void deleteAll();
}
