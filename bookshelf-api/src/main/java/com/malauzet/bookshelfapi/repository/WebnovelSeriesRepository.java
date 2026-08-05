package com.malauzet.bookshelfapi.repository;

import com.malauzet.bookshelfapi.model.WebnovelSeries;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WebnovelSeriesRepository extends JpaRepository<WebnovelSeries, Long> {
}
