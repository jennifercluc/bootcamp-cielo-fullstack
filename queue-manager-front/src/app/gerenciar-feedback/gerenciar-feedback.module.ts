import { CommonModule } from '@angular/common';
import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { AdicionarFeedBackComponent } from './adicionar-feedback/adicionar-feedback.component';
import { MatTableModule } from '@angular/material/table';
import { MatInputModule } from '@angular/material/input';
import { MatButtonModule } from '@angular/material/button';
import { MatDialogModule } from '@angular/material/dialog';

import { ListarFeedbackComponent } from './listar-feedback/listar-feedback.component';
import { DetalharFeedbackEnviadoComponent } from './detalhar-feedback-enviado/detalhar-feedback-enviado.component';
import { ModalContentComponent } from './detalhar-feedback-enviado/modal-content/modal-content.component';

@NgModule({
  declarations: [
    AdicionarFeedBackComponent,
    ListarFeedbackComponent,
    DetalharFeedbackEnviadoComponent,
    ModalContentComponent,
  ],
  imports: [
    CommonModule,
    FormsModule,
    MatInputModule,
    MatButtonModule,
    MatTableModule,
    MatDialogModule,
  ],
  exports: [
    AdicionarFeedBackComponent,
    ListarFeedbackComponent,
    DetalharFeedbackEnviadoComponent,
  ],
})
export class GerenciarFeedBackModule {}
