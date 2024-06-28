package org.example.models;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Book {
    private String isbn;
    private String title;
    private String author;
    private int publicationYear;
}
