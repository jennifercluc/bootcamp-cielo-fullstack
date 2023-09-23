import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AdicionarFeedBackComponent } from './gerenciar-feedback/adicionar-feedback/adicionar-feedback.component';

const routes: Routes = [
  {
    path: 'gerenciar',
    component: AdicionarFeedBackComponent,
  },
  { path: '', redirectTo: 'gerenciar', pathMatch: 'full' },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule],
})
export class AppRoutingModule {}
