import { Component, Inject } from '@angular/core';
import { MAT_DIALOG_DATA, MatDialogRef } from '@angular/material/dialog';
import { MatTableDataSource } from '@angular/material/table';
import { IFeedBackResponse } from 'src/app/shared/models/tipo-feedback.model';
import { FeedBackService } from 'src/app/shared/services/feedback.service';

@Component({
  selector: 'app-modal-content',
  templateUrl: './modal-content.component.html',
  styleUrls: ['./modal-content.component.scss'],
})
export class ModalContentComponent {
  displayedColumns: string[] = ['id', 'type', 'status', 'Message'];
  feedbacks: MatTableDataSource<IFeedBackResponse> =
    new MatTableDataSource<IFeedBackResponse>();

  constructor(
    public dialogRef: MatDialogRef<ModalContentComponent>,
    @Inject(MAT_DIALOG_DATA) public data: any,
    private feedBackService: FeedBackService
  ) {}

  ngOnInit() {
    this.loadFeedbacks();
  }

  loadFeedbacks() {
    this.feedBackService
      .getFeedbacks(this.data.tipoFeedBack)
      .subscribe((data: IFeedBackResponse[]) => {
        this.feedbacks = new MatTableDataSource(data);
      });
  }
}
