package com.malauzet.bookshelfapi.repository;

import com.malauzet.bookshelfapi.model.Manga;
import org.springframework.data.jpa.repository.JpaRepository;

/** CRUD for {@link Manga}. No custom queries beyond {@link JpaRepository} needed yet. */
public interface MangaRepository extends JpaRepository<Manga, Long> {
}
