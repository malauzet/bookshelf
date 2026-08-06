package com.malauzet.bookshelfapi.controller;

import com.malauzet.bookshelfapi.exception.MangaSeriesNotFoundException;
import com.malauzet.bookshelfapi.model.MangaSeries;
import com.malauzet.bookshelfapi.repository.MangaSeriesRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/manga-series")
@RequiredArgsConstructor
public class MangaSeriesController {

    private final MangaSeriesRepository mangaSeriesRepository;

    @PostMapping
    public ResponseEntity<MangaSeries> createMangaSeries(@RequestBody @Valid MangaSeries mangaSeries) {
        MangaSeries saved = mangaSeriesRepository.save(mangaSeries);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }

    @GetMapping
    public ResponseEntity<List<MangaSeries>> getAllMangaSeries() {
        return ResponseEntity.ok(mangaSeriesRepository.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<MangaSeries> getMangaSeriesById(@PathVariable Long id) {
        MangaSeries series = mangaSeriesRepository.findById(id)
                .orElseThrow(() -> new MangaSeriesNotFoundException("Manga series not found with id: " + id));
        return ResponseEntity.ok(series);
    }

    @PutMapping("/{id}")
    public ResponseEntity<MangaSeries> updateMangaSeries(@PathVariable Long id, @RequestBody @Valid MangaSeries mangaSeries) {
        MangaSeries existing = mangaSeriesRepository.findById(id)
                .orElseThrow(() -> new MangaSeriesNotFoundException("Manga series not found with id: " + id));

        existing.setName(mangaSeries.getName());
        existing.setAuthor(mangaSeries.getAuthor());
        existing.setTotalVolumes(mangaSeries.getTotalVolumes());

        MangaSeries updated = mangaSeriesRepository.save(existing);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMangaSeries(@PathVariable Long id) {
        if (!mangaSeriesRepository.existsById(id)) {
            throw new MangaSeriesNotFoundException("Manga series not found with id: " + id);
        }
        mangaSeriesRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
