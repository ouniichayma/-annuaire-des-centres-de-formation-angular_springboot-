import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Scategorie } from '../model/scategorie'
import { FormBuilder, FormGroup, FormControl, ReactiveFormsModule, Validators }
  from '@angular/forms';
@Injectable({
  providedIn: 'root'
})
export class ScategorieService {
  private baseUrl = 'http://localhost:8080/api/Scategories';
  private baseUrl1 = 'http://localhost:8080/api/Scategories/5';
  choixmenu: string = 'A';
  listData !: Scategorie[];

  public dataForm!: FormGroup;
  constructor(private http: HttpClient) { }



  listScateg(id: string): Observable<any> {
    return this.http.get(`${this.baseUrl1}/${id}`);
  }



  listScate(num_cat: number): Observable<any> {
    const url = `${this.baseUrl1}/${num_cat}`; // modify the URL to include the num_cat value as a path variable
    return this.http.get(url);
  }



  getData(id: string): Observable<Object> {
    return this.http.get(`${this.baseUrl}/${id}`);
  }

  createData(info: Object): Observable<Object> {
    return this.http.post(`${this.baseUrl}`, info);
  }

  updatedata(id: number, value: any): Observable<Object> {
    return this.http.put(`${this.baseUrl}/${id}`, value);
  }

  updateRang(id: number, value: any): Observable<Object> {
    return this.http.patch(`${this.baseUrl1}/${id}`, value);
  }
  deleteData(id: number): Observable<any> {

    return this.http.delete(`${this.baseUrl}/${id}`, { responseType: 'text' });
  }

  getAll(): Observable<any> {

    return this.http.get(`${this.baseUrl}`);
  }

  countScategories(): Observable<number> {
    return this.http.get<number>(`${this.baseUrl}/count-Scategorie`);
  }

}