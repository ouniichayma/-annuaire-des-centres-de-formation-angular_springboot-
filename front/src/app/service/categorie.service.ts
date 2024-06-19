import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Categorie } from '../model/categorie';
import { FormBuilder, FormGroup, FormControl, ReactiveFormsModule, Validators } from '@angular/forms';
@Injectable({
  providedIn: 'root'
})
export class CategorieService {
  private baseUrl = 'http://localhost:8080/api/Categories';
  private baseUrl2 = 'http://localhost:8080/api';
  choixmenu: string = 'A';
  listData!: Categorie[];
  public dataForm!: FormGroup;
  host: string = "http://localhost:8080";
  constructor(private http: HttpClient) {

  }


  searchByLibelle(libelle: string): Observable<any> {
    const url = `${this.baseUrl}/search?libelle=${libelle}`;
    return this.http.get(url);
  }

  getData(id: number): Observable<Object> {
    return this.http.get(`${this.baseUrl}`);
  }

 /* createData(info: Object): Observable<Object> {
    return this.http.post(`${this.baseUrl}`, info);
  }*/


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





  countCategories(): Observable<number> {
    return this.http.get<number>(`${this.baseUrl}/count-Categorie`);
  }
}