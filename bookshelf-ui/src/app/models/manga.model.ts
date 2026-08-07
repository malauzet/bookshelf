import { Series } from './series.model';
import { UserWork } from './user-work.model';
import { Work } from './work.model';

export interface MangaSeries extends Series {
}

export interface Manga extends Work {
    artist: string | null;
    totalPages: number | null;
    series: MangaSeries | null;
}

export type NewManga = Omit<Manga, 'id' | 'series'>;

export interface UserManga extends UserWork {
    currentPage: number | null;
}