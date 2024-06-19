import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Candidate } from '../model/candidate';


@Injectable({
  providedIn: 'root'
})
export class CandidateService {
  private baseUrl = 'http://localhost:8080/api/candidates';
  host: string = "http://localhost:8080/api/candidates/";
  constructor(private http: HttpClient) { }



 

  createCandidate(formData: FormData): Observable<any> {
    return this.http.post(`${this.baseUrl}`, formData);
  }



  getAllcandidate(): Observable<Candidate[]> {
    return this.http.get<Candidate[]>(`${this.baseUrl}`);
  }




}