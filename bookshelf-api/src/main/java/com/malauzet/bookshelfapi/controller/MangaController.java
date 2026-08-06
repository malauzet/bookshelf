package com.malauzet.bookshelfapi.controller;

import com.malauzet.bookshelfapi.exception.MangaNotFoundException;
import com.malauzet.bookshelfapi.exception.MangaSeriesNotFoundException;
import com.malauzet.bookshelfapi.model.Manga;
import com.malauzet.bookshelfapi.model.MangaSeries;
import com.malauzet.bookshelfapi.repository.MangaRepository;
import com.malauzet.bookshelfapi.repository.MangaSeriesRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Full CRUD for {@link Manga}. Series attachment goes through a {@code seriesId} query param
 * resolved server-side via {@link MangaSeriesRepository}, not a nested {@code series} object in
 * the request body — deserializing a partial nested entity from client JSON would be fragile
 * compared to a typed lookup by id.
 */
@RestController
@RequestMapping("/api/mangas")
@RequiredArgsConstructor
public class MangaController {

    private final MangaRepository mangaRepository;
    private final MangaSeriesRepository mangaSeriesRepository;

    /** @throws MangaSeriesNotFoundException if {@code seriesId} is given but doesn't resolve */
    @PostMapping
    public ResponseEntity<Manga> createManga(@RequestBody @Valid Manga manga, @RequestParam(required = false) Long seriesId) {

        if (seriesId != null) {
            MangaSeries series = mangaSeriesRepository.findById(seriesId)
                    .orElseThrow(() -> new MangaSeriesNotFoundException("Manga series not found with id: " + seriesId));
            manga.setSeries(series);
        }

        Manga saved = mangaRepository.save(manga);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }

    @GetMapping
    public ResponseEntity<List<Manga>> getAllMangas() {
        return ResponseEntity.ok(mangaRepository.findAll());
    }

    /** @throws MangaNotFoundException if no manga exists with the given id */
    @GetMapping("/{id}")
    public ResponseEntity<Manga> getMangaById(@PathVariable Long id) {
        Manga manga = mangaRepository.findById(id)
                .orElseThrow(() -> new MangaNotFoundException("Manga not found with id: " + id));
        return ResponseEntity.ok(manga);
    }

    /**
     * Full replace: every field in the request body overwrites the existing entity, including
     * {@code artist}. Omitting {@code seriesId} leaves the current series untouched — there is
     * currently no way to detach an already-assigned series via this endpoint.
     *
     * @throws MangaNotFoundException if no manga exists with the given id
     * @throws MangaSeriesNotFoundException if {@code seriesId} is given but doesn't resolve
     */
    @PutMapping("/{id}")
    public ResponseEntity<Manga> updateManga(@PathVariable Long id, @RequestBody @Valid Manga manga,
                                             @RequestParam(required = false) Long seriesId) {

        Manga existing = mangaRepository.findById(id)
                .orElseThrow(() -> new MangaNotFoundException("Manga not found with id: " + id));

        existing.setTitle(manga.getTitle());
        existing.setAuthor(manga.getAuthor());
        existing.setSynopsis(manga.getSynopsis());
        existing.setCoverImageUrl(manga.getCoverImageUrl());
        existing.setPublishedDate(manga.getPublishedDate());
        existing.setTotalChapters(manga.getTotalChapters());
        existing.setVolumeNumber(manga.getVolumeNumber());
        existing.setGenres(manga.getGenres());
        existing.setTotalPages(manga.getTotalPages());
        existing.setArtist(manga.getArtist());

        if (seriesId != null) {
            MangaSeries series = mangaSeriesRepository.findById(seriesId)
                    .orElseThrow(() -> new MangaSeriesNotFoundException("Manga series not found with id: " + seriesId));
            existing.setSeries(series);
        }

        Manga updated = mangaRepository.save(existing);
        return ResponseEntity.ok(updated);
    }

    /** @throws MangaNotFoundException if no manga exists with the given id */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteManga(@PathVariable Long id) {
        if (!mangaRepository.existsById(id)) {
            throw new MangaNotFoundException("Manga not found with id: " + id);
        }
        mangaRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
