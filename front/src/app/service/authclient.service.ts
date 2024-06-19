import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs/internal/Observable';
import { Client } from '../model/client';

@Injectable({
  providedIn: 'root'
})
export class AuthclientService {
  loginUrl : string = '';
  signUpUrl: string = '';
  updateUrl: string = '';
  getbyidUrl:  string = '';
  deletUrl:  string = '';
  baseurl:  string = '';
  constructor(private http : HttpClient) { 
    this.signUpUrl = "http://localhost:8080/authclient/register";
    this.loginUrl = "http://localhost:8080/authclient/login";
    this. getbyidUrl="http://localhost:8080/authclient/FindById/{id}";
    this.baseurl="http://localhost:8080/authclient";
  }

  login(client : Client) : Observable<any> {
    return this.http.post<any>(this.loginUrl,client);
  }

  signUp(dataForm : FormData) : Observable<any> {
    return this.http.post<any>(this.signUpUrl,dataForm);
  }

  update(client : Client) : Observable<any> {
    return this.http.put<any>(this.updateUrl,client);
  }



  getbyid(id: number) {
    return this.http.get<Client>(`${this.baseurl}/FindById/`+ id);

  }


  delet(id:number){
    return this.http.delete<Client>(this.deletUrl+id);
  }

}
