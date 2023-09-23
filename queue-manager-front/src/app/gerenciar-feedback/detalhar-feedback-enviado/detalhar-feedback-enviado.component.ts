import { Component } from '@angular/core';
import { FeedBackService } from '../../shared/services/feedback.service';
import { MatDialog } from '@angular/material/dialog';
import { ModalContentComponent } from './modal-content/modal-content.component';

@Component({
  selector: 'app-detalhar-feedback-enviado',
  templateUrl: './detalhar-feedback-enviado.component.html',
  styleUrls: ['./detalhar-feedback-enviado.component.scss'],
})
export class DetalharFeedbackEnviadoComponent {
  feedBackCount = [0, 0, 0];
  tiposFeedbacks = ['ELOGIO', 'CRITICA', 'SUGESTAO'];

  constructor(
    private feedbackService: FeedBackService,
    private dialog: MatDialog
  ) {}

  ngOnInit() {
    this.tiposFeedbacks.forEach((tipo) =>
      this.feedbackService.getFeedbackSizes(tipo).subscribe((count: number) => {
        switch (tipo) {
          case 'ELOGIO':
            this.feedBackCount[0] = count;
            break;
          case 'CRITICA':
            this.feedBackCount[1] = count;
            break;
          case 'SUGESTAO':
            this.feedBackCount[2] = count;
        }
      })
    );
  }

  openModal(tipoFeedBack: string) {
    const dialogRef = this.dialog.open(ModalContentComponent, {
      data: { tipoFeedBack },
    });
  }
}
