package com.malauzet.bookshelfapi.repository;

import com.malauzet.bookshelfapi.model.UserLightNovel;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Typed repository for {@link UserLightNovel}, needed for access to its format-specific
 * {@code currentPage} field, which isn't exposed on the generic {@code UserWorkRepository}.
 */
public interface UserLightNovelRepository extends JpaRepository<UserLightNovel, Long> {
}
