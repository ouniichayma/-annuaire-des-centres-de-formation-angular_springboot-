import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Categorie } from '../model/categorie';
import { FormBuilder, FormGroup, FormControl, ReactiveFormsModule, Validators } from '@angular/forms';
import { Formation } from '../model/formation';
@Injectable({
  providedIn: 'root'
})
export class FormationService {
  private baseUrl = 'http://localhost:8080/api/formations';
  private baseUrl2 = 'http://localhost:8080/api/formations/username';
  private baseurl3 = 'http://localhost:8080';
  host: string = "http://localhost:8080";

  choixmenu: string = 'A';
  listData!: Formation[];
  public dataForm!: FormGroup;
  constructor(private http: HttpClient) {}

  getItemsForUser(username: string): Observable<any> {
    const url = `${this.baseurl3}/formations/username/${username}`;
    return this.http.get(url);
  }

  getUserIdByUsername(username: string): Observable<number> {
    return this.http.get<number>(`${this.baseurl3}/api/users/${username}/id`);
  }

  getData(id: number): Observable<Object> {
    return this.http.get(`${this.baseUrl}/${id}`);
  }

  getbyusername(username: string): Observable<Object> {
    return this.http.get(`${this.baseUrl2}/${username}`);
  }



  createData(formdata: FormData): Observable<any> {
    return this.http.post(`${this.baseUrl}`, formdata);
  }

  updatedata(id: number, value: any): Observable<Object> {
    return this.http.put(`${this.baseUrl}/${id}`, value);
  }

  deleteData(id: number): Observable<any> {

    return this.http.delete(`${this.baseUrl}/${id}`, { responseType: 'text' });
  }


  getAll(): Observable<any> {

    return this.http.get(`${this.baseUrl}`);

  }


  countFormations(): Observable<number> {
    return this.http.get<number>(`${this.baseUrl}/count-formation`);
  }


}