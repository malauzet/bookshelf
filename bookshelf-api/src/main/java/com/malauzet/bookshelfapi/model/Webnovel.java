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
@DiscriminatorValue("WEBNOVEL")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Webnovel extends Work {

    @ManyToOne
    @JoinColumn(name = "series_id")
    private WebnovelSeries series;
}
