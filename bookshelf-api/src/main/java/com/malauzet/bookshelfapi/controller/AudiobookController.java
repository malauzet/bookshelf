package com.malauzet.bookshelfapi.controller;

import com.malauzet.bookshelfapi.exception.AudiobookNotFoundException;
import com.malauzet.bookshelfapi.exception.AudiobookSeriesNotFoundException;
import com.malauzet.bookshelfapi.model.Audiobook;
import com.malauzet.bookshelfapi.model.AudiobookSeries;
import com.malauzet.bookshelfapi.repository.AudiobookRepository;
import com.malauzet.bookshelfapi.repository.AudiobookSeriesRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/audiobooks")
@RequiredArgsConstructor
public class AudiobookController {

    private final AudiobookRepository audiobookRepository;
    private final AudiobookSeriesRepository audiobookSeriesRepository;

    @PostMapping
    public ResponseEntity<Audiobook> createAudiobook(@RequestBody @Valid Audiobook audiobook, @RequestParam(required = false) Long seriesId) {

        if (seriesId != null) {
            AudiobookSeries series = audiobookSeriesRepository.findById(seriesId)
                    .orElseThrow(() -> new AudiobookSeriesNotFoundException("Audiobook series not found with id: " + seriesId));
            audiobook.setSeries(series);
        }

        Audiobook saved = audiobookRepository.save(audiobook);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }

    @GetMapping
    public ResponseEntity<List<Audiobook>> getAllAudiobooks() {
        return ResponseEntity.ok(audiobookRepository.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Audiobook> getAudiobookById(@PathVariable Long id) {
        Audiobook audiobook = audiobookRepository.findById(id)
                .orElseThrow(() -> new AudiobookNotFoundException("Audiobook not found with id: " + id));
        return ResponseEntity.ok(audiobook);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Audiobook> updateAudiobook(@PathVariable Long id, @RequestBody @Valid Audiobook audiobook,
                                             @RequestParam(required = false) Long seriesId) {

        Audiobook existing = audiobookRepository.findById(id)
                .orElseThrow(() -> new AudiobookNotFoundException("Audiobook not found with id: " + id));

        existing.setTitle(audiobook.getTitle());
        existing.setAuthor(audiobook.getAuthor());
        existing.setSynopsis(audiobook.getSynopsis());
        existing.setCoverImageUrl(audiobook.getCoverImageUrl());
        existing.setPublishedDate(audiobook.getPublishedDate());
        existing.setTotalChapters(audiobook.getTotalChapters());
        existing.setVolumeNumber(audiobook.getVolumeNumber());
        existing.setGenres(audiobook.getGenres());
        existing.setTotalMinutes(audiobook.getTotalMinutes());
        existing.setNarrator(audiobook.getNarrator());

        if (seriesId != null) {
            AudiobookSeries series = audiobookSeriesRepository.findById(seriesId)
                    .orElseThrow(() -> new AudiobookSeriesNotFoundException("Audiobook series not found with id: " + seriesId));
            existing.setSeries(series);
        }

        Audiobook updated = audiobookRepository.save(existing);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAudiobook(@PathVariable Long id) {
        if (!audiobookRepository.existsById(id)) {
            throw new AudiobookNotFoundException("Audiobook not found with id: " + id);
        }
        audiobookRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
