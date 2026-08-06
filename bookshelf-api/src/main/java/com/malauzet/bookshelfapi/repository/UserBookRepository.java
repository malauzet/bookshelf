package com.malauzet.bookshelfapi.repository;

import com.malauzet.bookshelfapi.model.UserBook;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Typed repository for {@link UserBook}, needed for access to its format-specific
 * {@code currentPage} field, which isn't exposed on the generic {@code UserWorkRepository}.
 */
public interface UserBookRepository extends JpaRepository<UserBook, Long> {
}
