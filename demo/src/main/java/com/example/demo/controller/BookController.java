package com.example.demo.controller;




import com.example.demo.model.Book;
import com.example.demo.service.BookService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/books")

//BookController: Handles API requests from clients and passes them to the service
public class BookController {

    private final BookService service;

    public BookController(BookService service) {
        this.service = service;
    }

    // GET all books
    @GetMapping
    public List<Book> getAll() {
        return service.getAll();
    }

    // GET book by id
    @GetMapping("/{id}")
    public Book getOne(@PathVariable Long id) {
        return service.getOne(id);
    }

    // CREATE new book
    @PostMapping
    public Book create(@RequestBody Book b) {
        return service.create(b);
    }

    // UPDATE book by id
    @PutMapping("/{id}")
    public Book update(@PathVariable Long id, @RequestBody Book b) {
        return service.update(id, b);
    }

    // DELETE book by id
    @DeleteMapping("/{id}")
    public String deleteOne(@PathVariable Long id) {
        service.deleteOne(id);
        return "Book deleted: " + id;
    }

    // DELETE all books
    @DeleteMapping
    public String deleteAll() {
        service.deleteAll();
        return "All books removed.";
    }
}