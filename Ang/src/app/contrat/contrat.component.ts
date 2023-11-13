import { Component, OnInit } from '@angular/core';
import { ContratService } from 'src/app/service/contrat.service';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-contrat',
  templateUrl: './contrat.component.html',
  styleUrls: ['./contrat.component.scss'],
})
export class ContratComponent implements OnInit {
  contrats: any[] | undefined;
  contrat: any = {
    dateDebutContrat: '',
    dateFinContrat: '',
    specialite: '',
    archive: false,
    montantContrat: 0,
  };

  fg = new FormGroup({
    dateDebutContrat: new FormControl('', [Validators.required]),
    dateFinContrat: new FormControl('', [Validators.required]),
    specialite: new FormControl('', Validators.required),
    archive: new FormControl(false),
    montantContrat: new FormControl(0, Validators.required),
  });

  isAddPopupOpen: boolean = false;
  isUpdatePopupOpen: boolean = false;

  constructor(private contratService: ContratService) {}

  ngOnInit(): void {
    this.fetchContrats();
  }

  fetchContrats() {
    this.contratService.getContrats().subscribe((contrats) => {
      this.contrats = contrats;
    });
  }

  toggleAddPopup() {
    this.isAddPopupOpen = !this.isAddPopupOpen;
  }

  toggleUpdatePopup() {
    this.isUpdatePopupOpen = !this.isUpdatePopupOpen;
  }


  showSuccessNotification(data: any) {
    Swal.fire({
      icon: 'success',
      title: 'Success',
      text: data,
    });
  }
}
