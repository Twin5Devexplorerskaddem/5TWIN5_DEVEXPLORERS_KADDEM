// contrat-list.component.ts

import { Component, OnInit } from '@angular/core';
import { ContratService } from '../contrat.service';

@Component({
  selector: 'app-contrat-list',
  templateUrl: './contrat-list.component.html',
  styleUrls: ['./contrat-list.component.css'],
})
export class ContratListComponent implements OnInit {
  contrats: any[] = [];

  constructor(private contratService: ContratService) {}

  ngOnInit(): void {
    this.contratService.getAllContrats().subscribe((data) => {
      this.contrats = data;
    });
  }
}
