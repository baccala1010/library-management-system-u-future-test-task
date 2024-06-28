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
    public void testGetAllBooks() {
        Book book1 = new Book("9780553149661", "The Catcher in the Rye", "J.D. Salinger", 1951);
        Book book2 = new Book("9780140283297", "To Kill a Mockingbird", "Harper Lee", 1960);
        Book book3 = new Book("9780023042706", "The Prince", "Niccolo Machiavelli", 1976);
        Book book4 = new Book("9780140439199", "The Art of War", "Sun Tzu", 2003);
        bookService.addBook(book1);
        bookService.addBook(book2);
        bookService.addBook(book3);
        bookService.addBook(book4);

        List<Book> allBooks = bookService.getAllBooks();
        assertNotNull(allBooks);
        assertEquals(4, allBooks.size());
        assertEquals(book1, allBooks.get(0));
        assertEquals(book2, allBooks.get(1));
        assertEquals(book3, allBooks.get(2));
        assertEquals(book4, allBooks.get(3));
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
        //  1 - title
        //  2 - author
        //  3 - publication year
        //  4 - isbn

        // Search by title
        List<Book> searchResults = bookService.searchBooks(1, "The Catcher in the Rye");
        assertEquals(1, searchResults.size());
        assertEquals(book1, searchResults.get(0));

        // Search by author
        searchResults = bookService.searchBooks(2, "Harper Lee");
        assertEquals(1, searchResults.size());
        assertEquals(book2, searchResults.get(0));

        // Search by publication year
        searchResults = bookService.searchBooks(3, "1976");
        assertEquals(1, searchResults.size());
        assertEquals(book3, searchResults.get(0));

        // Search by ISBN
        searchResults = bookService.searchBooks(4, "9780140439199");
        assertEquals(1, searchResults.size());
        assertEquals(book4, searchResults.get(0));

        // Search by title with no match
        searchResults = bookService.searchBooks(1, "Nonexistent Book");
        assertTrue(searchResults.isEmpty());

        // Search by author with no match
        searchResults = bookService.searchBooks(2, "Unknown Author");
        assertTrue(searchResults.isEmpty());

        // Search by publication year with no match
        searchResults = bookService.searchBooks(3, "9999");
        assertTrue(searchResults.isEmpty());

        // Search by ISBN with no match
        searchResults = bookService.searchBooks(4, "0000000000000");
        assertTrue(searchResults.isEmpty());
    }
}
