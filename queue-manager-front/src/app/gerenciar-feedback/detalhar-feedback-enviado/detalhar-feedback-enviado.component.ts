import { Component } from '@angular/core';
import { FeedBackService } from '../../shared/services/feedback.service';
import { MatDialog } from '@angular/material/dialog';
import { ModalContentComponent } from './modal-content/modal-content.component';
import { EnumTipoFeedback } from 'src/app/shared/models/tipo-feedback.model';

@Component({
  selector: 'app-detalhar-feedback-enviado',
  templateUrl: './detalhar-feedback-enviado.component.html',
  styleUrls: ['./detalhar-feedback-enviado.component.scss'],
})
export class DetalharFeedbackEnviadoComponent {
  enumTipoFeedback = EnumTipoFeedback;
  feedBackCount: { [key: string]: number } = {};
  listaEnumTipoFeedback = Object.values(EnumTipoFeedback);

  constructor(
    private feedbackService: FeedBackService,
    private dialog: MatDialog
  ) {}

  ngOnInit() {
    this.listaEnumTipoFeedback.forEach((tipo) =>
      this.feedbackService.getFeedbackSizes(tipo).subscribe(
        (count: number) => {
          this.feedBackCount[tipo] = count;
        },
        (error) => console.log(error)
      )
    );
  }

  openModal(tipoFeedBack: string) {
    const dialogRef = this.dialog.open(ModalContentComponent, {
      data: { tipoFeedBack },
    });
  }
}
