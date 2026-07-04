package com.malauzet.bookshelfapi.repository;

import com.malauzet.bookshelfapi.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Long> {
}
