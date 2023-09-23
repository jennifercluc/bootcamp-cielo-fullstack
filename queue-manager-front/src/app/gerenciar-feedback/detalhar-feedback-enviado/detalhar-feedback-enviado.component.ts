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
  elogioCount: number = 0;
  criticaCount: number = 0;
  sugestaoCount: number = 0;

  constructor(
    private feedbackService: FeedBackService,
    private dialog: MatDialog
  ) {}

  ngOnInit() {
    this.feedbackService.getFeedbackSizes().subscribe((feedbackData: any) => {
      this.elogioCount = feedbackData.elogio;
      this.criticaCount = feedbackData.critica;
      this.sugestaoCount = feedbackData.sugestao;
    });
  }

  openModal(tipoFeedBack: string) {
    const dialogRef = this.dialog.open(ModalContentComponent, {
      data: { tipoFeedBack },
    });
  }
}
