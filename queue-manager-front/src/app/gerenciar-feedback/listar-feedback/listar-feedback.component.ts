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

  constructor(private feedBackService: FeedBackService) {}

  ngOnInit() {
    this.loadFeedbacks();
  }

  loadFeedbacks() {
    if (!this.tipoBusca)
      this.feedBackService
        .getTodosFeedbacks()
        .subscribe((data: IFeedBackResponse[]) => {
          this.feedbacks = new MatTableDataSource(data);
        });
    else
      this.feedBackService
        .getFeedbacks(this.tipoBusca)
        .subscribe((data: IFeedBackResponse[]) => {
          this.feedbacks = new MatTableDataSource(data);
        });
  }
}
