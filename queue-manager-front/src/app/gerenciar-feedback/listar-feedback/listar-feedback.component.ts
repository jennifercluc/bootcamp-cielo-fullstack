import { Component } from '@angular/core';
import { MatTableDataSource } from '@angular/material/table';
import { IFeedBackResponse } from 'src/app/shared/models/tipo-feedback.model';
import { FeedBackService } from 'src/app/shared/services/feedback.service';

@Component({
  selector: 'app-listar-feedback',
  templateUrl: './listar-feedback.component.html',
  styleUrls: ['./listar-feedback.component.scss'],
})
export class ListarFeedbackComponent {
  displayedColumns: string[] = ['id', 'tipo', 'status', 'mensagem'];
  feedbacks: MatTableDataSource<IFeedBackResponse> =
    new MatTableDataSource<IFeedBackResponse>();

  constructor(private feedBackService: FeedBackService) {}

  ngOnInit() {
    this.loadFeedbacks();
  }

  loadFeedbacks() {
    this.feedBackService
      .getTodosFeedbacks()
      .subscribe((data: IFeedBackResponse[]) => {
        this.feedbacks = new MatTableDataSource(data);
      });
  }
}
