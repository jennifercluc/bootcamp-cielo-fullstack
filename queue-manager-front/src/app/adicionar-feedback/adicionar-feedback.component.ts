import { Component } from '@angular/core';
import { FilaService } from '../shared/services/fila.service';
import { TipoFeedback } from '../shared/models/tipo-feedback.model';

@Component({
  selector: 'app-adicionar-feedback',
  templateUrl: './adicionar-feedback.component.html',
  styleUrls: ['./adicionar-feedback.component.scss'],
})
export class AdicionarFeedBackComponent {
  tiposDeFeedback: TipoFeedback[] = [
    new TipoFeedback(1, 'Sugestão'),
    new TipoFeedback(2, 'Crítica'),
    new TipoFeedback(3, 'Elogio'),
  ];
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
