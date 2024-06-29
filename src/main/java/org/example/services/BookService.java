package org.example.services;

import org.example.models.Book;
import org.example.repositories.BookRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class BookService implements BookRepository {

    private List<Book> books = new ArrayList<>();

    @Override
    public List<Book> getAllBooks() {
        return books;
    }

    @Override
    public void addBook(Book book) {
        books.add(book);
    }

    @Override
    public void deleteBook(Book book) {
        books.remove(book);
    }

    @Override
    public List<Book> searchBooks(String criteria, String value) {
        return books.stream()
                .filter(book -> {
                    switch (criteria) {
                        case "title":
                            return book.getTitle().contains(value);
                        case "author":
                            return book.getAuthor().contains(value);
                        case "publication year":
                            return String.valueOf(book.getPublicationYear()).contains(value);
                        case "isbn":
                            return book.getIsbn().contains(value);
                        default:
                            return false;
                    }
                })
                .collect(Collectors.toList());
    }
}
