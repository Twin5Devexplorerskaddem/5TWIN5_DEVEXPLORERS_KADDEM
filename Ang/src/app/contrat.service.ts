

import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class ContratService {
  private apiUrl = 'http://192.168.33.10:8089/kaddem/contrat/retrieve-all-contrats';

  constructor(private http: HttpClient) {}

  getAllContrats(): Observable<any> {
    return this.http.get(this.apiUrl);
  }
}
