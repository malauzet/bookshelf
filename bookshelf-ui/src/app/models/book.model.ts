import { Series } from './series.model';
import { UserWork } from './user-work.model';
import { Work } from './work.model';

export interface BookSeries extends Series {
}

export interface Book extends Work {
    totalPages: number | null;
    series: BookSeries | null;
}

export type NewBook = Omit<Book, 'id' | 'series'>;

export interface UserBook extends UserWork {
    currentPage: number | null;
}
