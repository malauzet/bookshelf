package com.malauzet.bookshelfapi.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Set;

/**
 * Abstract root of the {@code Work} hierarchy — a trackable piece of media, regardless of format.
 * <p>
 * Uses {@link InheritanceType#JOINED}: this class owns the columns common to every format
 * (title, author, synopsis, ...), while each concrete subclass ({@link Book}, {@link Webnovel},
 * {@link Audiobook}, {@link Manga}, {@link LightNovel}) owns only the columns specific to its
 * own format in its own joined table. The {@code work_type} discriminator column tells Hibernate
 * which subclass table to join and instantiate.
 * <p>
 * Reading progress/status/rating are deliberately not here — those belong to a given user's
 * relationship to a work, not the work itself, and live on {@link UserWork} instead.
 */
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name = "work_type", length = 20)
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public abstract class Work {

    /** Technical primary key, server-generated — never accepted from client input. */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Long id;

    @NotBlank(message = "Title is required")
    private String title;

    /**
     * Current credited author. Overwritten in place on update — does not track authorship
     * changes over the life of a series (see the conception doc's decision log).
     */
    @NotBlank(message = "Author is required")
    private String author;

    @Column(columnDefinition = "TEXT")
    private String synopsis;

    /** URL of an externally-hosted cover image; no image bytes are stored in the DB. */
    private String coverImageUrl;

    private LocalDate publishedDate;

    /** Total chapter count; {@code null} when unknown (e.g. a webnovel still being published). */
    @Positive(message = "Total chapters must be greater than 0")
    private Integer totalChapters;

    /**
     * Position of this entry within its {@code Series}, e.g. tome 3 of a light novel series.
     * {@code null} for a standalone work or an unnumbered entry. Lives here rather than on each
     * subclass because its meaning is identical across every format, unlike the typed
     * {@code series} reference each subclass declares individually.
     */
    @Positive(message = "Volume number must be greater than 0")
    private Integer volumeNumber;

    /** This work's genre tags; stored as a {@code work_genre} junction table, one row per genre. */
    @ElementCollection
    @Enumerated(EnumType.STRING)
    @CollectionTable(name = "work_genre", joinColumns = @JoinColumn(name = "work_id"))
    @Column(name = "genre")
    private Set<Genre> genres;
}
