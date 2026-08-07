import { HttpClient, HttpParams } from "@angular/common/http";
import { Observable } from "rxjs";

export abstract class WorkService<T> {
    
    protected constructor(protected http: HttpClient, protected baseUrl: string) {}

    getAll(): Observable<T[]> {
        return this.http.get<T[]>(this.baseUrl);
    }

    getById(id: number): Observable<T> {
        return this.http.get<T>(`${this.baseUrl}/${id}`);
    }

    create(newWork: Omit<T, 'id' | 'series'>, seriesId?: number): Observable<T> {
        const params = seriesId != null 
            ? new HttpParams().set('seriesId', seriesId) 
            : undefined;

        return this.http.post<T>(`${this.baseUrl}`, newWork, { params });
    }
  
    update(id: number, updatedItem: Omit<T, 'id' | 'series'>, seriesId?: number,): Observable<T> {
        const params = seriesId != null
            ? new HttpParams().set('seriesId', seriesId)
            : undefined;

        return this.http.put<T>(`${this.baseUrl}/${id}`, updatedItem, { params });
    }
 
    delete(id: number): Observable<void> {
        return this.http.delete<void>(`${this.baseUrl}/${id}`);
    }
}