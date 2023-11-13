import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { Routes, RouterModule } from '@angular/router';
import { NgApexchartsModule } from 'ng-apexcharts';
import { ContratComponent } from './contrat.component';

const routes: Routes = [
  {
    path: '',
    data: {
      title: 'Contrat',
      urls: [{ title: 'Contrat', url: '/contrat' }, { title: 'Contrat' }],
    },
    component: ContratComponent,
  },
];

@NgModule({
    imports: [
        FormsModule,
        ReactiveFormsModule,
        CommonModule,
        RouterModule.forChild(routes),
        NgApexchartsModule,
      ],
  declarations: [ContratComponent],
})
export class ContratModule {}
