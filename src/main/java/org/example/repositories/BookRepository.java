package org.example.repositories;

import org.example.models.Book;

import java.util.List;

public interface BookRepository {

    //  getAllBooks() : returns collection of Book class objects,
    //  return type : List<Book>,
    //  no parameters
    List<Book> getAllBooks();

    // addBook(Book book) : adds new Book class object into our collection of books,
    // no return type,
    // parameters : Book class object - Book book
    void addBook(Book book);

    // deleteBook(Book book) : removes book out of our books collection,
    // no return type,
    // parameters : Books class object - Book book
    void deleteBook(Book book);

    // searchBooks(String criteria, String value) : searches books that match given criteria and values,
    // return type : List<Book>
    // parameters: String criteria, String value
    List<Book> searchBooks(String criteria, String value);
}
