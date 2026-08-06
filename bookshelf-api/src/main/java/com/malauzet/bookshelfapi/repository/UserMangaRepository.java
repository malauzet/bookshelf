package com.malauzet.bookshelfapi.repository;

import com.malauzet.bookshelfapi.model.UserManga;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Typed repository for {@link UserManga}, needed for access to its format-specific
 * {@code currentPage} field, which isn't exposed on the generic {@code UserWorkRepository}.
 */
public interface UserMangaRepository extends JpaRepository<UserManga, Long> {
}
