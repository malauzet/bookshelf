package com.malauzet.bookshelfapi.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Abstract root of the {@code UserWork} hierarchy — the association between a {@link User} and a
 * {@link Work} they track: reading status, rating, and chapter progress. These belong here rather
 * than on {@code Work} itself, since the same work can be "FINISHED" for one user and
 * "PLAN_TO_READ" for another.
 * <p>
 * Uses {@link InheritanceType#JOINED} for the same reason as {@link Work} (not {@link Series}):
 * concrete subclasses ({@link UserBook}, {@link UserWebnovel}, {@link UserAudiobook},
 * {@link UserManga}, {@link UserLightNovel}) add real format-specific progress columns
 * ({@code currentPage} or {@code currentMinutes}).
 * <p>
 * Unlike {@code Series}, {@code user}/{@code work} deliberately stay on this parent class rather
 * than moving to each subclass: doing so would prevent {@code UNIQUE(user_id, work_id)} from
 * being expressible as a single-table SQL constraint. The trade-off is that nothing at the
 * schema level stops a {@code UserBook} from referencing a {@code Webnovel} — that guarantee
 * stays applicative, not structural, unlike the typed {@code series} FK on {@code Work}.
 * <p>
 * The {@code UNIQUE(user_id, work_id)} constraint also means a user has exactly one tracking row
 * per work, ever — there is currently no way to represent a re-read as a separate history entry.
 */
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name = "user_work_type", length = 20)
@Table(uniqueConstraints = @UniqueConstraint(columnNames = {"user_id", "work_id"}))
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public abstract class UserWork {

    /** Technical primary key, server-generated — never accepted from client input. */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Long id;

    /** This user's reading state for the work; {@code null} until they start tracking progress. */
    @Enumerated(EnumType.STRING)
    private ReadingStatus status;

    /** 1-10 inclusive; {@code null} until the user has rated the work. */
    @Min(value = 1, message = "Rating must be between 1 and 10")
    @Max(value = 10, message = "Rating must be between 1 and 10")
    private Integer rating;

    /** {@code null} for a work not yet started, e.g. status {@link ReadingStatus#PLAN_TO_READ}. */
    @Positive(message = "Current chapter must be greater than 0")
    private Integer currentChapter;

    /** The user tracking this work; read-only from the client's perspective. */
    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private User user;

    /** The work being tracked; read-only from the client's perspective. */
    @ManyToOne
    @JoinColumn(name = "work_id")
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Work work;
}
