package com.hilcoe.bookstore.bookstore.repository;

import com.hilcoe.bookstore.bookstore.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
    List<Book> findByAuthorId(Long authorId);
    List<Book> findByCategoryId(Long categoryId);

    // Custom query to flag low-stock inventory items
    @Query("SELECT b FROM Book b WHERE b.quantity <= b.minThreshold")
    List<Book> findLowStockBooks();
}