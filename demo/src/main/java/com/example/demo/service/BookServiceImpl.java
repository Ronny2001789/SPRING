package com.example.demo.service;

import com.example.demo.model.Book;
import com.example.demo.repository.BookRepository;
import org.springframework.stereotype.Service;

import java.util.List;

//BookServiceImpl: Contains the logic to create, update, delete, and fetch books
@Service
public class BookServiceImpl implements BookService {

    private final BookRepository repo;

    public BookServiceImpl(BookRepository repo) {
        this.repo = repo;
    }

    @Override
    public List<Book> getAll() {
        return repo.findAll();
    }

    @Override
    public Book create(Book b) {
        return repo.save(b);
    }

    @Override
    public Book getOne(Long id) {
        return repo.findById(id).orElse(null);
    }

    @Override
    public Book update(Long id, Book book) {
        Book existing = repo.findById(id).orElse(null);
        if (existing == null) return null;

        existing.setTitle(book.getTitle());
        existing.setAuthor(book.getAuthor());
        return repo.save(existing);
    }

    @Override
    public void deleteOne(Long id) {
        repo.deleteById(id);
    }

    @Override
    public void deleteAll() {
        repo.deleteAll();
    }
}
