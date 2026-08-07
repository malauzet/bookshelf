import { HttpClient, HttpParams } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { Observable } from "rxjs";
import { ReadingStatus } from "../models/enums.model";
import { UserWork, UserWorkUpdateRequest } from "../models/user-work.model";


@Injectable({ providedIn: 'root' })
export class UserWorkService {

    private readonly baseUrl = '/api/users';
 
    constructor(private http: HttpClient) {}
 
    getByStatus(userId: number, status: ReadingStatus): Observable<UserWork[]> {
        const params = new HttpParams().set('status', status);
 
        return this.http.get<UserWork[]>(`${this.baseUrl}/${userId}/user-works`, { params });
    }
 
    update(userId: number, id: number, changes: UserWorkUpdateRequest): Observable<UserWork> {
        return this.http.patch<UserWork>(`${this.baseUrl}/${userId}/user-works/${id}`, changes);
    }
 
    delete(userId: number, id: number): Observable<void> {
        return this.http.delete<void>(`${this.baseUrl}/${userId}/user-works/${id}`);
    }
}