package com.malauzet.bookshelfapi.model;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * A {@link Series} of {@link Webnovel}s. Adds no field of its own beyond {@link Series} — exists
 * so {@code Webnovel.series} can hold a foreign key typed specifically to this table,
 * structurally preventing a {@code Webnovel} from ever being attached to another format's series.
 */
@Entity
@DiscriminatorValue("WEBNOVEL")
@Getter
@Setter
@NoArgsConstructor
public class WebnovelSeries extends Series {
}
