// equipe.component.ts

import { Component, OnInit } from '@angular/core';
import { EquipeService } from '../equipe.service';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-equipe',
  templateUrl: './equipe.component.html',
  styleUrls: ['./equipe.component.scss']
})
export class EquipeComponent implements OnInit {
  equipes: any[] = [];
  isAddPopupOpen: boolean = false;
  isUpdatePopupOpen: boolean = false;  // Add this line
  addEquipeData: any = {};
  updateEquipeData: any = {};  // Add this line

  constructor(private equipeService: EquipeService) {}

  ngOnInit() {
    this.loadEquipes();
  }

  toggleAddPopup() {
    this.isAddPopupOpen = !this.isAddPopupOpen;
  }

  toggleUpdatePopup() {  // Add this method
    this.isUpdatePopupOpen = !this.isUpdatePopupOpen;
  }

  loadEquipes() {
    this.equipeService.getAllEquipes().subscribe((data: any) => {
      this.equipes = data;
    });
  }

  createEquipe() {
    this.equipeService.createEquipe(this.addEquipeData).subscribe(
      () => {
        this.loadEquipes();
        Swal.fire('Success', 'Équipe créée avec succès', 'success');
        this.toggleAddPopup();
      },
      (error) => {
        Swal.fire('Error', 'Échec de la création de l\'équipe', 'error');
      }
    );
  }

  updateEquipe(updatedData: any) {
    this.equipeService.updateEquipe(updatedData).subscribe(
      () => {
        this.loadEquipes();
        Swal.fire('Success', 'Équipe mise à jour avec succès', 'success');
        this.toggleUpdatePopup();
      },
      (error) => {
        Swal.fire('Error', 'Échec de la mise à jour de l\'équipe', 'error');
      }
    );
  }

  deleteEquipe(idEquipe: number) {
    this.equipeService.retrieveEquipe(idEquipe).subscribe(
      () => {
        this.loadEquipes();
        Swal.fire('Success', 'Équipe supprimée avec succès', 'success');
      },
      (error) => {
        Swal.fire('Error', 'Échec de la suppression de l\'équipe', 'error');
      }
    );
  }

  updateEquipePopup(equipe: any) {
    this.updateEquipeData = { ...equipe };  // Populate update data
    this.toggleUpdatePopup();
  }
}
