package org.example.repositories;

import org.example.models.Book;

import java.util.List;

public interface BookRepository {
    List<Book> getAllBooks();
    void addBook(Book book);
    void deleteBook(Book book);
    List<Book> searchBooks(String criteria, String value);
}
