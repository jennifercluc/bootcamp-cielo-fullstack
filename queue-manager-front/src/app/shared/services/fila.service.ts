import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root',
})
export class FilaService {
  private fila: any[] = [];
  constructor(private httpClient: HttpClient) {}

  adicionarNaFila(mensagem: string, tipo: string) {
    this.fila.push({ mensagem, tipo });
  }
}
