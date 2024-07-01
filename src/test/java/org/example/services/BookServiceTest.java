package org.example.services;

import org.example.models.Book;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class BookServiceTest {

    private BookService bookService;

    @Before
    public void setUp() {
        bookService = new BookService();
    }

    @Test
    public void testAddBook() {
        Book book = new Book("9780553149661", "The Catcher in the Rye", "J.D. Salinger", 1951);
        bookService.addBook(book);

        List<Book> books = bookService.getAllBooks();
        assertEquals(1, books.size());
        assertEquals(book, books.get(0));
    }

    @Test
    public void testDeleteBook() {
        Book book = new Book("9780553149661", "The Catcher in the Rye", "J.D. Salinger", 1951);
        bookService.addBook(book);
        bookService.deleteBook(book);

        List<Book> books = bookService.getAllBooks();
        assertTrue(books.isEmpty());
    }

    @Test
    public void testSearchBooks() {
        Book book1 = new Book("9780553149661", "The Catcher in the Rye", "J.D. Salinger", 1951);
        Book book2 = new Book("9780140283297", "To Kill a Mockingbird", "Harper Lee", 1960);
        Book book3 = new Book("9780023042706", "The Prince", "Niccolo Machiavelli", 1976);
        Book book4 = new Book("9780140439199", "The Art of War", "Sun Tzu", 2003);
        bookService.addBook(book1);
        bookService.addBook(book2);
        bookService.addBook(book3);
        bookService.addBook(book4);

        //  search criteria:
        //  title
        //  author
        //  publication year
        //  isbn

        // Search by title
        searchingTestFound("title", "The Catcher in the Rye", book1);

        // Search by author
        searchingTestFound("author", "Harper Lee", book2);

        // Search by publication year
        searchingTestFound("publication year", "1976", book3);

        // Search by ISBN
        searchingTestFound("isbn", "9780140439199", book4);

        // Search by title with no match
        searchingTestNotFound("title", "Nonexistent Book");

        // Search by author with no match
        searchingTestNotFound("author", "Unknown Author");

        // Search by publication year with no match
        searchingTestNotFound("publication year", "1333");

        // Search by ISBN with no match
        searchingTestNotFound("isbn", "456789087654");
    }

    private void searchingTestFound(String criteria, String value, Book book) {
        List<Book> searchResults = bookService.searchBooks(criteria, value);
        assertEquals(1, searchResults.size());
        assertEquals(book, searchResults.get(0));
    }

    private void searchingTestNotFound(String criteria, String value) {
        List<Book> searchResults = bookService.searchBooks(criteria, value);
        assertTrue(searchResults.isEmpty());
    }
}
