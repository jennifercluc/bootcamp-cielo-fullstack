import { CommonModule } from '@angular/common';
import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { AdicionarFeedBackComponent } from './adicionar-feedback.component';

import { MatInputModule } from '@angular/material/input';
import { MatButtonModule } from '@angular/material/button';

@NgModule({
  declarations: [AdicionarFeedBackComponent],
  imports: [CommonModule, FormsModule, MatInputModule, MatButtonModule],
  exports: [AdicionarFeedBackComponent],
})
export class AdicionarFeedBackModule {}
