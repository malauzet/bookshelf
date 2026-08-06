package com.malauzet.bookshelfapi.controller;

import com.malauzet.bookshelfapi.exception.BookSeriesNotFoundException;
import com.malauzet.bookshelfapi.model.BookSeries;
import com.malauzet.bookshelfapi.repository.BookSeriesRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/** Full CRUD for {@link BookSeries}. */
@RestController
@RequestMapping("/api/book-series")
@RequiredArgsConstructor
public class BookSeriesController {

    private final BookSeriesRepository bookSeriesRepository;

    @PostMapping
    public ResponseEntity<BookSeries> createBookSeries(@RequestBody @Valid BookSeries bookSeries) {
        BookSeries saved = bookSeriesRepository.save(bookSeries);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }

    @GetMapping
    public ResponseEntity<List<BookSeries>> getAllBookSeries() {
        return ResponseEntity.ok(bookSeriesRepository.findAll());
    }

    /** @throws BookSeriesNotFoundException if no series exists with the given id */
    @GetMapping("/{id}")
    public ResponseEntity<BookSeries> getBookSeriesById(@PathVariable Long id) {
        BookSeries series = bookSeriesRepository.findById(id)
                .orElseThrow(() -> new BookSeriesNotFoundException("Book series not found with id: " + id));
        return ResponseEntity.ok(series);
    }

    /**
     * Full replace: every field in the request body overwrites the existing entity.
     *
     * @throws BookSeriesNotFoundException if no series exists with the given id
     */
    @PutMapping("/{id}")
    public ResponseEntity<BookSeries> updateBookSeries(@PathVariable Long id, @RequestBody @Valid BookSeries bookSeries) {
        BookSeries existing = bookSeriesRepository.findById(id)
                .orElseThrow(() -> new BookSeriesNotFoundException("Book series not found with id: " + id));

        existing.setName(bookSeries.getName());
        existing.setAuthor(bookSeries.getAuthor());
        existing.setTotalVolumes(bookSeries.getTotalVolumes());

        BookSeries updated = bookSeriesRepository.save(existing);
        return ResponseEntity.ok(updated);
    }

    /** @throws BookSeriesNotFoundException if no series exists with the given id */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBookSeries(@PathVariable Long id) {
        if (!bookSeriesRepository.existsById(id)) {
            throw new BookSeriesNotFoundException("Book series not found with id: " + id);
        }
        bookSeriesRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
