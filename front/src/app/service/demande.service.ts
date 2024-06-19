import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Demande } from '../model/demande';

@Injectable({
  providedIn: 'root'
})
export class DemandeService {

  



  private baseUrl = 'http://localhost:8080/api/demandes';

  constructor(private http: HttpClient) { }

  addDemande(demande: Demande): Observable<Demande> {
    return this.http.post<Demande>(this.baseUrl, demande);
  }

  getAllDemandes(): Observable<Demande[]> {
    return this.http.get<Demande[]>(`${this.baseUrl}/getall`);
  }


  countDemandes(): Observable<number> {
    return this.http.get<number>(`${this.baseUrl}/count-Demandes`);
  }



  getDemandeById(id: number): Observable<Demande> {
    return this.http.get<Demande>(`${this.baseUrl}/${id}`);
  }

  updateDemande(id: number, demande: Demande): Observable<Demande> {
    return this.http.put<Demande>(`${this.baseUrl}/update/${id}`, demande);
  }

  deleteDemande(id: number): Observable<void> {
    return this.http.delete<void>(`${this.baseUrl}/delete/${id}`);
  }
}

