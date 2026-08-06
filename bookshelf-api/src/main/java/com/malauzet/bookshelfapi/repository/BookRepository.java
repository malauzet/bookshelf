package com.malauzet.bookshelfapi.repository;

import com.malauzet.bookshelfapi.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;

/** CRUD for {@link Book}. No custom queries beyond {@link JpaRepository} needed yet. */
public interface BookRepository extends JpaRepository<Book, Long> {
}
