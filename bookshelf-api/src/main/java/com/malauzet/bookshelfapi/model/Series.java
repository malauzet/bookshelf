package com.malauzet.bookshelfapi.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Abstract root of the {@code Series} hierarchy — an ordered collection of {@link Work} entries
 * of a single format (a book saga, a webnovel, ...).
 * <p>
 * Uses {@link InheritanceType#JOINED} for a different reason than {@link Work}: every concrete
 * subclass ({@link BookSeries}, {@link WebnovelSeries}, {@link AudiobookSeries},
 * {@link MangaSeries}, {@link LightNovelSeries}) adds no field of its own — they exist purely so
 * each {@code Work} subclass can hold a <em>typed</em> foreign key to its own series table
 * (e.g. {@code book.series_id} can only reference {@code book_series}), making cross-format
 * mixups impossible at the schema level rather than by convention alone.
 */
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name = "series_type", length = 20)
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public abstract class Series {

    /** Technical primary key, server-generated — never accepted from client input. */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Long id;

    /**
     * Not unique: two unrelated series (different authors) may legitimately share a title,
     * so a uniqueness constraint would reject a real, non-duplicate case.
     */
    @NotBlank(message = "Series' name is required")
    private String name;

    /**
     * Required specifically so two same-titled series can still be told apart. Like
     * {@link Work#getAuthor()}, this is a single overwritable value with no change history.
     */
    @NotBlank(message = "Author is required")
    private String author;

    /** Total number of volumes planned/published for the series; {@code null} while ongoing. */
    private Integer totalVolumes;
}
