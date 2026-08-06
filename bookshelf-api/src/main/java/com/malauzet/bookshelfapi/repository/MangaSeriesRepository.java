package com.malauzet.bookshelfapi.repository;

import com.malauzet.bookshelfapi.model.MangaSeries;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Typed repository for {@link MangaSeries}. Needed specifically because {@code Manga.series}
 * takes a {@code MangaSeries} directly — a root repository typed to the abstract {@code Series}
 * would return {@code Optional<Series>}, requiring an unsafe cast to assign it.
 */
public interface MangaSeriesRepository extends JpaRepository<MangaSeries, Long> {
}
