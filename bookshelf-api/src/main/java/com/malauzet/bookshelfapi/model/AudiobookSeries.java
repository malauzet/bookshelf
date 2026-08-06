package com.malauzet.bookshelfapi.model;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * A {@link Series} of {@link Audiobook}s. Adds no field of its own beyond {@link Series} — exists
 * so {@code Audiobook.series} can hold a foreign key typed specifically to this table,
 * structurally preventing an {@code Audiobook} from ever being attached to another format's
 * series.
 */
@Entity
@DiscriminatorValue("AUDIOBOOK")
@Getter
@Setter
@NoArgsConstructor
public class AudiobookSeries extends Series {
}
