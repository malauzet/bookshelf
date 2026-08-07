import { ReadingStatus } from './enums.model';

export interface UserWork {
    id: number;
    status: ReadingStatus | null;
    rating: number | null;
    currentChapter: number | null;
}