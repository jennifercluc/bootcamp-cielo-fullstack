import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, catchError, throwError } from 'rxjs';
import { IFeedBack } from '../models/tipo-feedback.model';

@Injectable({
  providedIn: 'root',
})
export class FeedBackService {
  constructor(private httpClient: HttpClient) {}
  private backendUrl = 'http://localhost:8080/api/feedback';

  enviarFeedBack(feedBack: IFeedBack): Observable<any> {
    return this.httpClient.post<any>(this.backendUrl + '/envio', feedBack);
  }
}
