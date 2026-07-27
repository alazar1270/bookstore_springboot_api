package com.hilcoe.bookstore.bookstore.controller;

import com.hilcoe.bookstore.bookstore.model.Book;
import com.hilcoe.bookstore.bookstore.service.BookService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/books")
public class BookController {

    @Autowired
    private BookService bookService;

    @GetMapping
    public List<Book> getAllBooks() {
        return bookService.getAllBooks();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Book> getBookById(@PathVariable Long id) {
        return bookService.getBookById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/author/{authorId}")
    public List<Book> getBooksByAuthor(@PathVariable Long authorId) {
        return bookService.getBooksByAuthor(authorId);
    }

    @GetMapping("/category/{categoryId}")
    public List<Book> getBooksByCategory(@PathVariable Long categoryId) {
        return bookService.getBooksByCategory(categoryId);
    }

    @PostMapping
    public ResponseEntity<Book> createBook(@Valid @RequestBody Book book) {
        Book savedBook = bookService.saveBook(book);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedBook);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Book> updateBook(@PathVariable Long id, @Valid @RequestBody Book bookDetails) {
        return bookService.getBookById(id)
                .map(existingBook -> {
                    bookDetails.setId(id);
                    Book updatedBook = bookService.saveBook(bookDetails);
                    return ResponseEntity.ok(updatedBook);
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBook(@PathVariable Long id) {
        bookService.deleteBook(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/inventory/low-stock")
    public List<Book> getLowStockBooks() {
        return bookService.getLowStockBooks();
    }
}