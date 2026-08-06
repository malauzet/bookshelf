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

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name = "user_work_type", length = 20)
@Table(uniqueConstraints = @UniqueConstraint(columnNames = {"user_id", "work_id"}))
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public abstract class UserWork {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private ReadingStatus status;

    @Min(value = 1, message = "Rating must be between 1 and 10")
    @Max(value = 10, message = "Rating must be between 1 and 10")
    private Integer rating;

    @Positive(message = "Current chapter must be greater than 0")
    private Integer currentChapter;

    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private User user;

    @ManyToOne
    @JoinColumn(name = "work_id")
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Work work;
}
