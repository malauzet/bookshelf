package com.malauzet.bookshelfapi.repository;

import com.malauzet.bookshelfapi.model.UserWebnovel;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Typed repository for {@link UserWebnovel}. {@code UserWebnovel} adds no field beyond
 * {@code UserWork}, so this exists mainly for consistency with the other tracked formats and for
 * the {@code POST} "track" endpoint's return type.
 */
public interface UserWebnovelRepository extends JpaRepository<UserWebnovel, Long> {
}
