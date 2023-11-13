import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';

import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class ServiceEtudiantService {

  private baseUrl ='http://172.10.0.140:8089/Kaddem/etudiant';

  
  constructor(private http: HttpClient) {}

  getEtudiants(): Observable<any[]> {
    return this.http.get<any[]>('http://172.10.0.140:8089/kaddem/etudiant/retrieve-all-etudiants');
  }

  getEtudiant(etudiantId: number): Observable<any> {
    return this.http.get<any>(`${this.baseUrl}/retrieve-etudiant/${etudiantId}`);
  }

 
  addEtudiant(etudiant: any): Observable<any> {
    const httpOptions = {
      headers: new HttpHeaders({
        'Content-Type': 'application/json',
      }),
      withCredentials: true, // Include this if your server requires credentials
    };

    return this.http.post<any>('http://172.10.0.140:8089/kaddem/etudiant/add-etudiant', etudiant, httpOptions);
  }

  removeEtudiant(etudiantId: number): Observable<void> {
    return this.http.delete<void>(`${this.baseUrl}/remove-etudiant/${etudiantId}`);
  }

  updateEtudiant(etudiant: any): Observable<any> {
    return this.http.put<any>(`${this.baseUrl}/update-etudiant`, etudiant);
  }

  assignEtudiantToDepartement(etudiantId: number, departementId: number): Observable<void> {
    return this.http.put<void>(
      `${this.baseUrl}/affecter-etudiant-departement/${etudiantId}/${departementId}`,
      null
    );
  }

  addEtudiantWithEquipeAndContract(
    etudiant: any,
    idContrat: number,
    idEquipe: number
  ): Observable<any> {
    return this.http.post<any>(
      `${this.baseUrl}/add-assign-Etudiant/${idContrat}/${idEquipe}`,
      etudiant
    );
  }

  getEtudiantsByDepartement(idDepartement: number): Observable<any[]> {
    return this.http.get<any[]>(`${this.baseUrl}/getEtudiantsByDepartement/${idDepartement}`);
  }

}
