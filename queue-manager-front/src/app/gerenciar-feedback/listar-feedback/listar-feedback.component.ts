import { IFeedbackAllResponse } from './../../shared/models/tipo-feedback.model';
import { Component, Input } from '@angular/core';
import { MatTableDataSource } from '@angular/material/table';
import {
  EnumTipoFeedback,
  IFeedBackResponse,
} from 'src/app/shared/models/tipo-feedback.model';
import { FeedBackService } from 'src/app/shared/services/feedback.service';

@Component({
  selector: 'app-listar-feedback',
  templateUrl: './listar-feedback.component.html',
  styleUrls: ['./listar-feedback.component.scss'],
})
export class ListarFeedbackComponent {
  @Input()
  tipoBusca: string = '';

  displayedColumns: string[] = ['MessageId', 'type', 'status', 'Message'];
  feedbacks: MatTableDataSource<IFeedBackResponse> =
    new MatTableDataSource<IFeedBackResponse>();

  listaEnumTipoFeedback = Object.values(EnumTipoFeedback);

  constructor(private feedBackService: FeedBackService) {}

  ngOnInit() {
    this.loadFeedbacks();
  }

  loadFeedbacks() {
    if (!this.tipoBusca)
      this.feedBackService
        .getTodosFeedbacks()
        .subscribe((data: IFeedbackAllResponse) => {
          this.listaEnumTipoFeedback.forEach((item) => {
            this.feedbacks.data.concat(data[item]);
          });
        });
    else
      this.feedBackService
        .getFeedbacks(this.tipoBusca)
        .subscribe((data: IFeedBackResponse[]) => {
          this.feedbacks = new MatTableDataSource(data);
        });
  }
}
