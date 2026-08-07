import { Series } from './series.model';
import { UserWork } from './user-work.model';
import { Work } from './work.model';

export interface WebnovelSeries extends Series {
}

export interface Webnovel extends Work {
    series: WebnovelSeries | null;
}

export type NewWebnovel = Omit<Webnovel, 'id' | 'series'>;

export interface UserWebnovel extends UserWork {
}