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

/**
 * A serialized online {@link Work}, optionally belonging to a {@link WebnovelSeries}. The
 * leanest concrete {@code Work} subclass — no format-specific fields beyond the inherited ones.
 */
@Entity
@DiscriminatorValue("WEBNOVEL")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Webnovel extends Work {

    /**
     * Typed FK: can only reference a {@link WebnovelSeries}, never another format's series table.
     * {@code null} for a standalone webnovel. Read-only over the API — assigned server-side via a
     * {@code seriesId} query param on create/update, not deserialized from nested JSON.
     */
    @ManyToOne
    @JoinColumn(name = "series_id")
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private WebnovelSeries series;
}
