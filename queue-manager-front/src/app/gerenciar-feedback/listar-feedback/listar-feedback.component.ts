import { Component } from '@angular/core';
import { MatTableDataSource } from '@angular/material/table';
import { FeedBackService } from 'src/app/shared/services/feedback.service';

@Component({
  selector: 'app-listar-feedback',
  templateUrl: './listar-feedback.component.html',
  styleUrls: ['./listar-feedback.component.scss'],
})
export class ListarFeedbackComponent {
  displayedColumns: string[] = ['id', 'tipo', 'status', 'mensagem'];
  feedbacks: MatTableDataSource<any> = new MatTableDataSource<any>();

  constructor(private feedBackService: FeedBackService) {}

  ngOnInit() {
    this.loadFeedbacks();
  }

  loadFeedbacks() {
    // TODO: Remover mock this.feedBackService.getFeedbacks().subscribe((data: any[]) => {
    this.feedBackService.getFeedbacksMock().subscribe((data: any[]) => {
      this.feedbacks = new MatTableDataSource(data);
    });
  }
}
