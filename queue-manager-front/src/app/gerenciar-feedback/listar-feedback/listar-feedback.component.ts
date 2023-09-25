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

  displayedColumns: string[] = ['id', 'type', 'status', 'Message'];
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
          var listaFeedBack: IFeedBackResponse[] = [];
          this.listaEnumTipoFeedback.forEach((item) => {
            listaFeedBack = listaFeedBack.concat(data[item]);
          });
          console.log(listaFeedBack);
          this.feedbacks = new MatTableDataSource(listaFeedBack);
        });
    else
      this.feedBackService
        .getFeedbacks(this.tipoBusca)
        .subscribe((data: IFeedBackResponse[]) => {
          this.feedbacks = new MatTableDataSource(data);
        });
  }
}
