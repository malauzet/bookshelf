package com.malauzet.bookshelfapi.repository;

import com.malauzet.bookshelfapi.model.Audiobook;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AudiobookRepository extends JpaRepository<Audiobook, Long> {
}
