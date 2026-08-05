package com.malauzet.bookshelfapi.repository;

import com.malauzet.bookshelfapi.model.UserBook;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserBookRepository extends JpaRepository<UserBook, Long> {
}
