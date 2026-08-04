package com.malauzet.bookshelfapi.model;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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
    private Integer totalMinutes;

    @ManyToOne
    @JoinColumn(name = "series_id")
    private AudiobookSeries series;
}
