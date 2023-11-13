import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class ContratService {
  private apiUrl = 'http://192.168.33.10:8089';

  constructor(private http: HttpClient) {}

  

  // Read
  getContrats(): Observable<any[]> {
    return this.http.get<any[]>(`${this.apiUrl}/kaddem/contrat/retrieve-all-contrats`);
  }

 
}
