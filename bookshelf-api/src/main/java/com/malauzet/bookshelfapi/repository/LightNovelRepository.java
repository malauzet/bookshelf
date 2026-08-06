package com.malauzet.bookshelfapi.repository;

import com.malauzet.bookshelfapi.model.LightNovel;
import org.springframework.data.jpa.repository.JpaRepository;

/** CRUD for {@link LightNovel}. No custom queries beyond {@link JpaRepository} needed yet. */
public interface LightNovelRepository extends JpaRepository<LightNovel, Long> {
}
