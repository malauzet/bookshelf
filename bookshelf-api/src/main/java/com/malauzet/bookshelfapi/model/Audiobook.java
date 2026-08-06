package com.malauzet.bookshelfapi.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@DiscriminatorValue("AUDIOBOOK")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Audiobook extends Work {

    private String narrator;

    @Positive(message = "Total minutes must be greater than 0")
    private Integer totalMinutes;

    @ManyToOne
    @JoinColumn(name = "series_id")
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private AudiobookSeries series;
}
