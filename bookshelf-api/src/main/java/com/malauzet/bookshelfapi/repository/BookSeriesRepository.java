package com.malauzet.bookshelfapi.repository;

import com.malauzet.bookshelfapi.model.BookSeries;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Typed repository for {@link BookSeries}. Needed specifically because {@code Book.series} takes
 * a {@code BookSeries} directly — a root repository typed to the abstract {@code Series} would
 * return {@code Optional<Series>}, requiring an unsafe cast to assign it.
 */
public interface BookSeriesRepository extends JpaRepository<BookSeries, Long> {
}
