import { Genre } from './enums.model';

export interface Work {
    id: number;
    title: string;
    author: string;

    synopsis: string | null;
    coverImageUrl: string | null;
    publishedDate: string | null;

    totalChapters: number | null;
    volumeNumber: number | null;

    genres: Genre[];
}