package com.books.dto.book;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetBookDto {

    @NotNull(message = "Book ID year must be provided")
    private Integer bookId;

    @NotBlank(message = "Book name cannot be blank")
    private String bookName;

    @NotBlank(message = "Author name cannot be blank")
    private String author;

    @NotBlank(message = "Title cannot be blank")
    private String title;

    @Column(unique = true)
    @NotBlank(message = "ISBN cannot be blank")
    private String isbn;

    @NotBlank(message = "Publisher cannot be blank")
    private String publisher;

    @NotBlank(message = "Language cannot be blank")
    private String language;

    @NotBlank(message = "Cover description cannot be blank")
    private String cover;

    @NotNull(message = "Published year must be provided")
    private int publishedYear;

    @NotNull(message = "Quantity must be provided")
    private int quantity;

    @NotNull(message = "Price must be provided")
    private Double price;

    @NotNull(message = "Category ID must be provided")
    private Integer categoryId;

    @NotNull(message = "Image ID cannot be null")
    private Integer photoFileId;
}
