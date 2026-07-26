/*
// Mon premier essaie à TypeScript, en dessous la version corrigée avec commentaires.
export type ReadingStatus = 'READING' | 'WAITING' | 'PLAN_TO_READ' | 'FINISHED';

export interface Book {
  id: number;
  title: string;
  author: string;
  status: ReadingStatus;
  coverImageUrl?: string;
  synopsis?: string;
  genre?: string;
  series?: string;
  currentChapter: number | null;
  totalChapters: number;
  publishedDate?: string;
  language?: string;
  rating?: number;
}*/

export type ReadingStatus = 'READING' | 'WAITING' | 'PLAN_TO_READ' | 'FINISHED';

export interface Book {
  id: number;
  title: string;
  author: string;
  status: ReadingStatus;

  // `| null` (not `?`) on everything below: this type models the JSON coming back
  // from GET /api/books. Jackson serializes every Java field, always — an unset
  // field shows up as a present key with value `null`, never a missing key.
  // `?` means "key might be missing", which is the WRONG shape for server responses.
  // (`?` is still correct for request bodies you build yourself in a form — see NewBook below.)
  coverImageUrl: string | null;
  synopsis: string | null;
  genre: string | null;
  series: string | null;

  // Both currentChapter and totalChapters are Integer (not int) in the Java entity,
  // specifically so they can be null for a book with no known chapter count
  // (e.g. an ongoing web novel). Mirrored here as nullable, not optional.
  currentChapter: number | null;
  totalChapters: number | null;

  publishedDate: string | null; // Java LocalDate -> ISO string over JSON, not a Date object
  language: string | null;
  rating: number | null; // Java Integer 1-5, validated server-side; null = unrated
}

// Utility type: Omit<T, K> builds a new type = T minus the listed keys, without
// retyping every field by hand. Use this (not Book) as the type for the object
// you build in the create-book form and send in a POST body — a new book has
// no `id` yet, the backend assigns one via @GeneratedValue.
export type NewBook = Omit<Book, 'id'>;
