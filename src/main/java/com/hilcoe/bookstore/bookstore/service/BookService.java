package com.hilcoe.bookstore.bookstore.service;

import com.hilcoe.bookstore.bookstore.model.Author;
import com.hilcoe.bookstore.bookstore.model.Book;
import com.hilcoe.bookstore.bookstore.model.Category;
import com.hilcoe.bookstore.bookstore.repository.AuthorRepository;
import com.hilcoe.bookstore.bookstore.repository.BookRepository;
import com.hilcoe.bookstore.bookstore.repository.CategoryRepository;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private AuthorRepository authorRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    public Optional<Book> getBookById(Long id) {
        return bookRepository.findById(id);
    }

    @Transactional
    public Book saveBook(Book book) {
        // 1. Handle Author
        if (book.getAuthor() != null) {
            if (book.getAuthor().getId() != null) {
                // Look up existing author
                Author existingAuthor = authorRepository.findById(book.getAuthor().getId())
                        .orElseThrow(() -> new RuntimeException("Author not found with ID: " + book.getAuthor().getId()));
                book.setAuthor(existingAuthor);
            } else {
                // Save new author on the fly
                Author newAuthor = authorRepository.save(book.getAuthor());
                book.setAuthor(newAuthor);
            }
        }

        // 2. Handle Category
        if (book.getCategory() != null) {
            if (book.getCategory().getId() != null) {
                // Look up existing category
                Category existingCategory = categoryRepository.findById(book.getCategory().getId())
                        .orElseThrow(() -> new RuntimeException("Category not found with ID: " + book.getCategory().getId()));
                book.setCategory(existingCategory);
            } else {
                // Save new category on the fly
                Category newCategory = categoryRepository.save(book.getCategory());
                book.setCategory(newCategory);
            }
        }

        // 3. Save and return the book
        return bookRepository.save(book);
    }

    public List<Book> getBooksByAuthor(Long authorId) {
        return bookRepository.findByAuthorId(authorId);
    }

    public List<Book> getBooksByCategory(Long categoryId) {
        return bookRepository.findByCategoryId(categoryId);
    }

    public void deleteBook(Long id) {
        bookRepository.deleteById(id);
    }

    public List<Book> getLowStockBooks() {
        return bookRepository.findLowStockBooks();
    }
}