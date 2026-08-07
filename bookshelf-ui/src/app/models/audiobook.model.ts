import { Series } from './series.model';
import { UserWork } from './user-work.model';
import { Work } from './work.model';

export interface AudiobookSeries extends Series {
}

export interface Audiobook extends Work {
    narrator: string | null;
    totalMinutes: number | null;
    series: AudiobookSeries | null;
}

export type NewAudiobook = Omit<Audiobook, 'id' | 'series'>;

export interface UserAudiobook extends UserWork {
    currentMinutes: number | null;
}