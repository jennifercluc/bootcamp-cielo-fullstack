import { HttpClient, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, catchError, of, throwError } from 'rxjs';
import { IFeedBack, IFeedBackResponse } from '../models/tipo-feedback.model';

@Injectable({
  providedIn: 'root',
})
export class FeedBackService {
  constructor(private httpClient: HttpClient) {}
  private apiUrl = 'http://localhost:8080/api/feedback';

  enviarFeedback(feedback: IFeedBack): Observable<string> {
    return this.httpClient.post(this.apiUrl + '/envio', feedback, {
      responseType: 'text',
    });
  }

  getTodosFeedbacks(): Observable<IFeedBackResponse[]> {
    return this.httpClient.get<IFeedBackResponse[]>(`${this.apiUrl}/info/all`);
  }

  getFeedbacks(tipo: string): Observable<IFeedBackResponse[]> {
    const params = new HttpParams().set('type', tipo);
    return this.httpClient.get<IFeedBackResponse[]>(`${this.apiUrl}/info`, {
      params,
    });
  }

  getFeedbackSizes(tipo: string): Observable<number> {
    const params = new HttpParams().set('type', tipo);
    return this.httpClient.get<number>(`${this.apiUrl}/tamanho`, {
      params,
    });
  }
}
