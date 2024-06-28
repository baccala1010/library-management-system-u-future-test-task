package org.example;

import org.example.exceptions.BookNotFoundException;
import org.example.exceptions.InvalidInputException;
import org.example.models.Book;
import org.example.services.BookService;

import java.util.List;
import java.util.Scanner;

public class Main {
    static Scanner sc = new Scanner(System.in);
    public static void main(String[] args) {
        BookService bookService = new BookService();
        System.out.println("Welcome to Library Management System!");

        while (true) {
            System.out.println("Please choose the action to do:");
            System.out.println("1. Add book");
            System.out.println("2. Delete book");
            System.out.println("3. Search books");
            System.out.println("4. Show all books");
            System.out.println("5. Exit");

            int choice = sc.nextInt();

            switch (choice) {
                case 1:
                    System.out.print("Please enter the book ISBN: ");
                    String isbn = sc.next();
                    System.out.print("Please enter the title: ");
                    String title = sc.next();
                    System.out.print("Please enter the author's name: ");
                    String author = sc.next();
                    System.out.print("Please enter the publication year: ");
                    int publicationYear = sc.nextInt();

                    bookService.addBook(new Book(isbn, title, author, publicationYear));
                    break;

                case 2:
                    System.out.println("Please enter the ISBN of the book to delete:");
                    String deleteIsbn = sc.next();

                    List<Book> booksToDelete = bookService.searchBooks(4, deleteIsbn);

                    if (booksToDelete.isEmpty()) {
                        throw new BookNotFoundException("Book not found");
                    } else {
                        bookService.deleteBook(booksToDelete.get(0));
                    }
                    break;

                case 3:
                    System.out.println("Please enter the criteria for searching:");
                    System.out.println("1. Title");
                    System.out.println("2. Author");
                    System.out.println("3. Publication Year");
                    System.out.println("4. ISBN");

                    int searchCriteriaChoice = sc.nextInt();

                    System.out.println("Please enter the search value:");
                    String value = sc.next();

                    List<Book> searchResults = bookService.searchBooks(searchCriteriaChoice, value);

                    for (Book book : searchResults) {
                        System.out.println(book);
                    }
                    break;

                case 4:
                    System.out.println("Here is the list of all books:");
                    List<Book> allBooks = bookService.getAllBooks();
                    for (Book book : allBooks) {
                        System.out.println(book);
                    }
                    break;

                case 5:
                    System.exit(0);
                    break;
                default:
                    throw new InvalidInputException("Invalid input...");
            }
        }
    }
}