package com.hilcoe.bookstore.bookstore.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

@Entity
@Table(name = "books")
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "ISBN is required")
    @Column(nullable = false, unique = true)
    private String isbn;

    @NotBlank(message = "Title is required")
    @Column(nullable = false)
    private String title;

    @NotNull(message = "Price is required")
    @Positive(message = "Price must be greater than 0")
    private Double price;

    @NotNull(message = "Quantity is required")
    @Min(value = 0, message = "Quantity cannot be negative")
    private Integer quantity;

    @NotNull(message = "Minimum threshold is required")
    @Min(value = 0, message = "Minimum threshold cannot be negative")
    private Integer minThreshold;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "author_id")
    private Author author;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "category_id")
    private Category category;

    // Default Constructor required by JPA/Jackson
    public Book() {}

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getIsbn() { return isbn; }
    public void setIsbn(String isbn) { this.isbn = isbn; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public Double getPrice() { return price; }
    public void setPrice(Double price) { this.price = price; }

    public Integer getQuantity() { return quantity; }
    public void setQuantity(Integer quantity) { this.quantity = quantity; }

    public Integer getMinThreshold() { return minThreshold; }
    public void setMinThreshold(Integer minThreshold) { this.minThreshold = minThreshold; }

    public Author getAuthor() { return author; }
    public void setAuthor(Author author) { this.author = author; }

    public Category getCategory() { return category; }
    public void setCategory(Category category) { this.category = category; }
}