# BookShelf

A personal book/webnovel tracker — think Goodreads-style shelves (Reading / Hiatus / Dropped / Plan to Read / Finished) for tracking what you're reading, your rating, and progress through chapters or volumes.

This is a learning project: a return to Java/Spring after time away, and a first real project in Angular/TypeScript. Built incrementally, end to end, with an emphasis on understanding each layer rather than scaffolding it and moving on.

## Status

Backend data model is built around a `Work`/`Series`/`UserWork` class hierarchy — separate entities per format (`Book`, `Webnovel`, `Audiobook`, `Manga`, `LightNovel`), each with its own typed series, plus per-user reading state (status, rating, progress) kept separate from the work itself. Verified end-to-end against a generated H2 schema. The REST API has been rebuilt against this new model for all five formats — user registration, series, works, and per-user tracking (status/rating/progress) are all live and tested for `Book`, `Webnovel`, `LightNovel`, `Manga`, and `Audiobook`. Frontend is scaffolded with a first (now outdated) TypeScript model in place; UI components are next, now unblocked by a complete API surface. See [Roadmap](#roadmap).

## Tech stack

**Backend** — `bookshelf-api/`
- Java 21, Spring Boot, Spring Data JPA, Spring Web, Bean Validation
- H2 (in-memory), Maven, Lombok

**Frontend** — `bookshelf-ui/`
- Angular, TypeScript
- `ng serve` dev-server proxy to the backend (no CORS config needed in dev)

## Running locally

**Backend** (from `bookshelf-api/`):
```bash
./mvnw spring-boot:run
```
H2 console at `http://localhost:8080/h2-console` (JDBC URL `jdbc:h2:mem:bookshelfdb`, user `sa`, no password). REST endpoints are live for all five `Work` formats, see [Roadmap](#roadmap).

**Frontend** (from `bookshelf-ui/`):
```bash
npm install
npm start
```
App available at `http://localhost:4200`, proxying API calls to the backend above.

## API

Rebuilt against the `Work`/`Series`/`UserWork` model for all five formats (`Book`, `Webnovel`, `LightNovel`, `Manga`, `Audiobook`):

- `POST /api/users`, `GET /api/users/{id}` — registration and lookup
- `POST/GET/PUT/DELETE /api/book-series`, `/api/webnovel-series`, `/api/light-novel-series`, `/api/manga-series`, `/api/audiobook-series` — series CRUD
- `POST/GET/PUT/DELETE /api/books`, `/api/webnovels`, `/api/light-novels`, `/api/mangas`, `/api/audiobooks` — works CRUD, with optional series attachment
- `POST /api/users/{userId}/books/{bookId}` (and the equivalent per format) — start tracking a work (format-specific, since it constructs the matching `UserBook`/`UserWebnovel`/etc.)
- `GET /api/users/{userId}/user-works?status=...`, `PATCH`/`DELETE /api/users/{userId}/user-works/{id}` — per-user reading tracking (status, rating, progress), shared across every tracked format, including a status filter for the tabbed UI

Structured JSON error responses (HTTP 400/404/409) via a global exception handler, rather than raw stack traces, is the approach used throughout.

## Roadmap

- [x] REST API (repositories, controllers, error handling) rebuilt against the new `Work`/`Series`/`UserWork` model — done for all five formats
- [ ] Angular UI: book list, detail view, create/edit form
- [ ] PostgreSQL + Flyway migrations (replacing H2 in-memory)
- [ ] Cover image / metadata lookup via Google Books or Open Library APIs
- [ ] Authentication
