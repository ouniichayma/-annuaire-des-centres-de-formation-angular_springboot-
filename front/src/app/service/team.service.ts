import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { FormGroup } from '@angular/forms';
import { Observable } from 'rxjs';
import { Team } from '../model/team';

@Injectable({
  providedIn: 'root'
})
export class TeamService {

  private baseUrl = 'http://localhost:8080/api/teams';
  private baseUrl2 = 'http://localhost:8080/api/teams/username';
  private baseurl3 = 'http://localhost:8080';
  host: string = "http://localhost:8080";

  choixmenu: string = 'A';
  listData!: Team[];
  public dataForm!: FormGroup;
  constructor(private http: HttpClient) {

  }

  getItemsForUser(username: string): Observable<any> {
    const url = `${this.baseurl3}/teams/username/${username}`;
    return this.http.get(url);
  }


  getUserIdByUsername(username: string): Observable<number> {
    return this.http.get<number>(`${this.baseurl3}/api/user/${username}/id`);
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
    return this.http.put(`${this.baseurl3}/${id}`, value);
  }

  deleteData(id: number): Observable<any> {

    return this.http.delete(`${this.baseUrl}/${id}`, { responseType: 'text' });
  }


  getAll(): Observable<any> {

    return this.http.get(`${this.baseUrl}`);

  }

}
