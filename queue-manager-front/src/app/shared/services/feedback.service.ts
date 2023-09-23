import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, catchError, of, throwError } from 'rxjs';
import { IFeedBack } from '../models/tipo-feedback.model';

@Injectable({
  providedIn: 'root',
})
export class FeedBackService {
  constructor(private httpClient: HttpClient) {}
  private apiUrl = 'http://localhost:8080/api/feedback';

  enviarFeedBack(feedBack: IFeedBack): Observable<any> {
    return this.httpClient.post<any>(this.apiUrl + '/envio', feedBack);
  }

  getFeedbacks(): Observable<any[]> {
    return this.httpClient.get<any[]>(`${this.apiUrl}/feedbacks`);
  }

  private feedbacks = [
    {
      id: 1,
      tipo: 'Elogio',
      status: 'Recebido',
      mensagem: 'Ótimo trabalho! O aplicativo está incrível.',
    },
    {
      id: 2,
      tipo: 'Crítica',
      status: 'Em Processamento',
      mensagem: 'O design poderia ser melhorado.',
    },
    {
      id: 3,
      tipo: 'Sugestão',
      status: 'Finalizado',
      mensagem: 'Adicione mais recursos de pesquisa.',
    },
  ];

  getFeedbacksMock(): Observable<any[]> {
    return of(this.feedbacks); // Simulando uma chamada assíncrona
  }
}
