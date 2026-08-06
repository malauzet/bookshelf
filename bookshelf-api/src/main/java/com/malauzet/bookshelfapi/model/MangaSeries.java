package com.malauzet.bookshelfapi.model;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * A {@link Series} of {@link Manga}. Adds no field of its own beyond {@link Series} — exists so
 * {@code Manga.series} can hold a foreign key typed specifically to this table, structurally
 * preventing a {@code Manga} from ever being attached to another format's series.
 */
@Entity
@DiscriminatorValue("MANGA")
@Getter
@Setter
@NoArgsConstructor
public class MangaSeries extends Series {
}