package com.malauzet.bookshelfapi.repository;

import com.malauzet.bookshelfapi.model.Webnovel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WebnovelRepository extends JpaRepository<Webnovel, Long> {
}
