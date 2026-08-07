import { Series } from './series.model';
import { UserWork } from './user-work.model';
import { Work } from './work.model';

export interface LightNovelSeries extends Series {
}

export interface LightNovel extends Work {
    artist: string | null;
    totalPages: number | null;
    series: LightNovelSeries | null;
}

export type NewLightNovel = Omit<LightNovel, 'id' | 'series'>;

export interface UserLightNovel extends UserWork {
    currentPage: number | null;
}