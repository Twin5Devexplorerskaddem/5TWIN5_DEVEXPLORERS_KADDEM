// etudiant.component.ts

import { Component, OnInit } from '@angular/core';
import { ServiceEtudiantService } from 'src/app/service/service-etudiant.service';

@Component({
  selector: 'app-etudiant',
  templateUrl: './etudiant.component.html',
  // styleUrls: ['./etudiant.component.css'],
})
export class EtudiantComponent implements OnInit {
  etudiants: any[] | undefined;
  newEtudiant: any = {}; // Used for adding a new Etudiant
  selectedEtudiant: any = {}; // Used for updating and deleting Etudiant

  constructor(private etudiantService: ServiceEtudiantService) {}

  ngOnInit() {
    this.getEtudiants();
  }

  getEtudiants() {
    this.etudiantService.getEtudiants().subscribe((data) => {
      this.etudiants = data;
    });
  }


  addEtudiant() {
    this.etudiantService.addEtudiant(this.newEtudiant).subscribe(() => {
      this.getEtudiants();
      this.newEtudiant = {};
    });
  }
  

  updateEtudiant() {
    this.etudiantService.updateEtudiant(this.selectedEtudiant).subscribe(() => {
      this.getEtudiants();
      this.selectedEtudiant = {}; // Clear the form
    });
  }

  removeEtudiant(etudiantId: number) {
    this.etudiantService.removeEtudiant(etudiantId).subscribe(() => {
      this.getEtudiants();
    });
  }

  selectEtudiant(etudiant: any) {
    this.selectedEtudiant = { ...etudiant }; // Create a copy to avoid two-way binding issues
  }
}
