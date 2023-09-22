import { Component } from '@angular/core';
import { FilaService } from '../shared/services/fila.service';

@Component({
  selector: 'app-adicionar-feedback',
  templateUrl: './adicionar-feedback.component.html',
  styleUrls: ['./adicionar-feedback.component.scss'],
})
export class AdicionarFeedBackComponent {
  mensagem: string = '';
  tipo: string = '';

  constructor(private filaService: FilaService) {}

  adicionarNaFila() {
    if (this.mensagem && this.tipo) {
      this.filaService.adicionarNaFila(this.mensagem, this.tipo);
      this.mensagem = '';
      this.tipo = '';
    }
  }
}
