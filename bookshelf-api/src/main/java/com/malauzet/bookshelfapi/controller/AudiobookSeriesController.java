package com.malauzet.bookshelfapi.controller;

import com.malauzet.bookshelfapi.exception.AudiobookSeriesNotFoundException;
import com.malauzet.bookshelfapi.model.AudiobookSeries;
import com.malauzet.bookshelfapi.repository.AudiobookSeriesRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/** Full CRUD for {@link AudiobookSeries}. */
@RestController
@RequestMapping("/api/audiobook-series")
@RequiredArgsConstructor
public class AudiobookSeriesController {

    private final AudiobookSeriesRepository audiobookSeriesRepository;

    @PostMapping
    public ResponseEntity<AudiobookSeries> createAudiobookSeries(@RequestBody @Valid AudiobookSeries audiobookSeries) {
        AudiobookSeries saved = audiobookSeriesRepository.save(audiobookSeries);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }

    @GetMapping
    public ResponseEntity<List<AudiobookSeries>> getAllAudiobookSeries() {
        return ResponseEntity.ok(audiobookSeriesRepository.findAll());
    }

    /** @throws AudiobookSeriesNotFoundException if no series exists with the given id */
    @GetMapping("/{id}")
    public ResponseEntity<AudiobookSeries> getAudiobookSeriesById(@PathVariable Long id) {
        AudiobookSeries series = audiobookSeriesRepository.findById(id)
                .orElseThrow(() -> new AudiobookSeriesNotFoundException("Audiobook series not found with id: " + id));
        return ResponseEntity.ok(series);
    }

    /**
     * Full replace: every field in the request body overwrites the existing entity.
     *
     * @throws AudiobookSeriesNotFoundException if no series exists with the given id
     */
    @PutMapping("/{id}")
    public ResponseEntity<AudiobookSeries> updateAudiobookSeries(@PathVariable Long id, @RequestBody @Valid AudiobookSeries audiobookSeries) {
        AudiobookSeries existing = audiobookSeriesRepository.findById(id)
                .orElseThrow(() -> new AudiobookSeriesNotFoundException("Audiobook series not found with id: " + id));

        existing.setName(audiobookSeries.getName());
        existing.setAuthor(audiobookSeries.getAuthor());
        existing.setTotalVolumes(audiobookSeries.getTotalVolumes());

        AudiobookSeries updated = audiobookSeriesRepository.save(existing);
        return ResponseEntity.ok(updated);
    }

    /** @throws AudiobookSeriesNotFoundException if no series exists with the given id */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAudiobookSeries(@PathVariable Long id) {
        if (!audiobookSeriesRepository.existsById(id)) {
            throw new AudiobookSeriesNotFoundException("Audiobook series not found with id: " + id);
        }
        audiobookSeriesRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
