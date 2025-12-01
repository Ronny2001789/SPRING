package com.example.demo.repository;

import com.example.demo.model.Book;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class BookRepository {

    private Map<Long, Book> db = new HashMap<>();
    private long idCounter = 1;

    public List<Book> findAll() {
        return new ArrayList<>(db.values());
    }

    public Book save(Book book) {
        if (book.getId() == 0) { // new book
            book.setId(idCounter++);
        }
        db.put(book.getId(), book);
        return book;
    }

    public Optional<Book> findById(Long id) {
        return Optional.ofNullable(db.get(id));
    }

    public void deleteById(Long id) {
        db.remove(id);
    }

    public void deleteAll() {
        db.clear();
    }
}
