import { HttpClient } from "@angular/common/http";
import { Observable } from "rxjs";

export abstract class UserTrackingService<T> {
    
    protected constructor(protected http: HttpClient, protected resourceSegment: string) {}

    track(userId: number, workId: number, newItem: Omit<T, 'id'>): Observable<T> {
        return this.http.post<T>(`/api/users/${userId}/${this.resourceSegment}/${workId}`, newItem);
    }
}