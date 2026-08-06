package com.malauzet.bookshelfapi.repository;

import com.malauzet.bookshelfapi.model.Audiobook;
import org.springframework.data.jpa.repository.JpaRepository;

/** CRUD for {@link Audiobook}. No custom queries beyond {@link JpaRepository} needed yet. */
public interface AudiobookRepository extends JpaRepository<Audiobook, Long> {
}
