package com.malauzet.bookshelfapi.repository;

import com.malauzet.bookshelfapi.model.BookSeries;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookSeriesRepository extends JpaRepository<BookSeries, Long> {
}
