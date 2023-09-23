import { CommonModule } from '@angular/common';
import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { AdicionarFeedBackComponent } from './adicionar-feedback/adicionar-feedback.component';
import { MatTableModule } from '@angular/material/table';
import { MatInputModule } from '@angular/material/input';
import { MatButtonModule } from '@angular/material/button';
import { ListarFeedbackComponent } from './listar-feedback/listar-feedback.component';

@NgModule({
  declarations: [AdicionarFeedBackComponent, ListarFeedbackComponent],
  imports: [
    CommonModule,
    FormsModule,
    MatInputModule,
    MatButtonModule,
    MatTableModule,
  ],
  exports: [AdicionarFeedBackComponent, ListarFeedbackComponent],
})
export class GerenciarFeedBackModule {}
