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
  dataTable: any[] = [];
  feedbacks: MatTableDataSource<IFeedBackResponse> =
    new MatTableDataSource<IFeedBackResponse>();

  tiposFeedbacks = ['ELOGIO', 'CRITICA', 'SUGESTAO'];

  constructor(private feedBackService: FeedBackService) {}

  ngOnInit() {
    this.loadFeedbacks();
  }

  loadFeedbacks() {
    this.tiposFeedbacks.forEach((tipo) =>
      this.feedBackService
        .getFeedbacks(tipo)
        .subscribe((data: IFeedBackResponse[]) => {
          console.log(data);
          this.dataTable.concat(data);
          this.feedbacks = new MatTableDataSource(this.dataTable);
        })
    );
  }
}
