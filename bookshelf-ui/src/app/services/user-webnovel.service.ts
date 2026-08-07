import { HttpClient } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { UserTrackingService } from "./user-tracking.service";
import { UserWork } from "../models/user-work.model";

@Injectable({ providedIn: 'root' })
export class UserWebnovelService extends UserTrackingService<UserWork> {

    constructor(http: HttpClient) {
        super(http, 'webnovels');
    }
}