import { Component, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';

@Component({
  selector: 'app-etudiant',
  templateUrl: './etudiant.component.html',
  styleUrls: ['./etudiant.component.css']
})
export class EtudiantComponent implements OnInit {
  etudiants: any[] = []; // Replace 'any' with the actual type of your data if known

  constructor(private http: HttpClient) { }

  ngOnInit() {
    this.fetchEtudiants();
  }

  fetchEtudiants() {
    const url = 'http://172.10.0.140:8089/kaddem/etudiant/retrieve-all-etudiants';

    this.http.get<any[]>(url).subscribe(
      (data) => {
        // Handle the response data here
        this.etudiants = data;
        console.log(data)
      },
      (error) => {
        // Handle errors here
        console.error(error);
      }
    );
  }
}
