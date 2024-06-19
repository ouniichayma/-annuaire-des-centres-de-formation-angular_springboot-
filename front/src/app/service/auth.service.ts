import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, tap } from 'rxjs';
import { environment } from 'src/environments/environment';
import { User } from '../model/user';

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  private isLoggedIn = false;

 

 
 


  findByUsernameUrl = 'http://localhost:8080/auth/findByUsername';
  findByLocalisationUrl = 'http://localhost:8080/auth/localisation';
  baseurl = 'http://localhost:8080/auth';
  host = "http://localhost:8080";


  baseUrl = environment.baseUrl;

  constructor(private http: HttpClient) {

   
   


   
   
   

    this.findByUsernameUrl = 'http://localhost:8080/auth/findByUsername';
    this.findByLocalisationUrl = 'http://localhost:8080/auth/localisation';
    this.baseurl = 'http://localhost:8080/auth';
    this.host = "http://localhost:8080";
   
  }

  /* login(user : User) : Observable<any> {
     return this.http.post<any>(this.loginUrl,user);
   }*/


  login(user: User): Observable<any> {
    return this.http.post<any>(`${this.baseurl}/login`, user).pipe(
      tap(res => {
        if (res && res.token) {
          // Set the isLoggedIn property to true
          this.isLoggedIn = true;
        }
      })
    );
  }


  logout(): void {
    // Perform the logout logic, e.g. delete session cookie, etc.
    this.isLoggedIn = false;
  }


  isAuthenticated(): boolean {
    // Return the authentication status
    return this.isLoggedIn;
  }

  signUp(dataForm: FormData): Observable<any> {
    return this.http.post<any>(`${this.baseurl}/register`, dataForm);
  }

  update(user: User): Observable<any> {
    return this.http.put<any>(`${this.baseurl}/update`, user);
  }
  getbyid(id: number) {
    return this.http.get<User>(`${this.baseurl}/FindById/`+ id);

  }
  delet(id: number) {
    return this.http.delete<User>(`${this.baseurl}/delete/`+ id);
  }
  getactive(id: number) {
    return this.http.get<boolean>(`${this.baseurl}/FindBy/`+ id);

  }
  getAll(): Observable<any> {

    return this.http.get(`${this.baseurl}/Alluser`);
  }


  getInactiveUsers(): Observable<User[]> {
    return this.http.get<User[]>(`${this.baseurl}/inactive-users`);
  }


  getactiveUsers(): Observable<User[]> {
    return this.http.get<User[]>(`${this.baseurl}/active-users`);
  }

  countInactiveUsers(): Observable<number> {
    return this.http.get<number>(`${this.baseurl}/count-inactive`);
  }
  countactiveUsers(): Observable<number> {
    return this.http.get<number>(`${this.baseurl}/count-active`);
  }


  // La méthode in authservice angular pour appeler l'endpoint Spring findByUsername

  findByUsername(username: string): Observable<User> {
    return this.http.get<User>(`${this.findByUsernameUrl}/${username}`);
  }


  // La méthode in authservice angular pour appeler l'endpoint Spring findByLocalisation

  findByLocalisation(localisation: string): Observable<User[]> {
    return this.http.get<User[]>(`${this.findByLocalisationUrl}/${localisation}`);
  }



}
