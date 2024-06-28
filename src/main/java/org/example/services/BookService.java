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
    public List<Book> searchBooks(int criteria, String value) {
        return books.stream()
                .filter(book -> {
                    switch (criteria) {
                        case 1:
                            return book.getTitle().contains(value);
                        case 2:
                            return book.getAuthor().contains(value);
                        case 3:
                            return String.valueOf(book.getPublicationYear()).contains(value);
                        case 4:
                            return book.getIsbn().contains(value);
                        default:
                            return false;
                    }
                })
                .collect(Collectors.toList());
    }
}
