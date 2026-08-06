package com.malauzet.bookshelfapi.repository;

import com.malauzet.bookshelfapi.model.Webnovel;
import org.springframework.data.jpa.repository.JpaRepository;

/** CRUD for {@link Webnovel}. No custom queries beyond {@link JpaRepository} needed yet. */
public interface WebnovelRepository extends JpaRepository<Webnovel, Long> {
}
