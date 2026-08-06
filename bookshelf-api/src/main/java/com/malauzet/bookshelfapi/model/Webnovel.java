package com.malauzet.bookshelfapi.model;

import com.fasterxml.jackson.annotation.JsonProperty;
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
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private WebnovelSeries series;
}
