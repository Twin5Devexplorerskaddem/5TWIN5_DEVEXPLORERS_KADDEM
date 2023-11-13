// equipe.service.ts
import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class EquipeService {
  private equipeUrl = 'http://localhost:8089/equipe';

  constructor(private http: HttpClient) {}

  getAllEquipes(): Observable<any[]> {
    return this.http.get<any[]>(`${this.equipeUrl}/retrieve-all-equipes`);
  }

  createEquipe(equipeData: any): Observable<any> {
    return this.http.post(`${this.equipeUrl}/add-equipe`, equipeData);
  }

  updateEquipe(equipeData: any): Observable<any> {
    return this.http.put(`${this.equipeUrl}/update-equipe`, equipeData);
  }

  retrieveEquipe(idEquipe: number): Observable<any> {
    const url = `${this.equipeUrl}/retrieve-equipe/${idEquipe}`;
    return this.http.get(url);
  }
}
