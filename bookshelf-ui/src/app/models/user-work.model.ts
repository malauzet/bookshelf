import { ReadingStatus } from './enums.model';

export interface UserWork {
    id: number;
    status: ReadingStatus | null;
    rating: number | null;
    currentChapter: number | null;
}

export interface UserWorkUpdateRequest {
    status?: ReadingStatus;
    rating?: number;
    currentChapter?: number;
    currentPage?: number;
    currentMinutes?: number;
}