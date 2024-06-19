import { Component, OnInit } from '@angular/core';

import { FormationService} from '../../service/formation.service'

import { FormBuilder, FormGroup, FormControl, ReactiveFormsModule,Validators }

from '@angular/forms';
import { Router } from '@angular/router';
import { Formation} from '../../model/formation';
import { ToastrService } from 'ngx-toastr';
import { Observable, observable, of } from 'rxjs';
import { AuthService } from 'src/app/service/auth.service';
import { HttpClient } from '@angular/common/http';
import { User } from 'src/app/model/user';

@Component({
  selector: 'app-user-dashboard',
  templateUrl: './user-dashboard.component.html',
  styleUrls: ['./user-dashboard.component.css']
})
export class UserDashboardComponent implements OnInit {
  username : any;
  user: User | null = null; // Change type to allow null values

  formation!: Formation;
  
  control: FormControl = new FormControl('');
  listData!: Observable<Formation[]>;
  baseurl = "http://localhost:8080/api";
  modeSwitch: any;
 



  constructor(public crudApi: FormationService, public toastr: ToastrService,
    private router : Router,public fb: FormBuilder, public authService : AuthService ,private http:HttpClient) { }


  ngOnInit(): void {

    this.getData();
    this.username=localStorage.getItem('username');
    const id = localStorage.getItem('id');
    if (id) {
      this.authService.getbyid(+id).subscribe(user => {
        this.user = user;
      });
    }



  }

  filter () {
    document.querySelector(".filter-menu")?.classList.toggle("active");
  };


  logout() {
    this.authService.logout();
  }

 
 
  

  grid () {
    document.querySelector(".list")?.classList.remove("active");
    document.querySelector(".grid")?.classList.add("active");
    document.querySelector(".products-area-wrapper")?.classList.add("gridView");
    document
      .querySelector(".products-area-wrapper")?.classList.remove("tableView")
  }
  
list(){
  
  document.querySelector(".list")?.classList.add("active");
  document.querySelector(".grid")?.classList.remove("active");
  document.querySelector(".products-area-wrapper")?.classList.remove("gridView");
  document.querySelector(".products-area-wrapper")?.classList.add("tableView");

}


 
  
  switch() {
    const html = document.querySelector('html');
    html?.classList.toggle('dark-mode');
    const modeSwitch = document.querySelector<HTMLElement>('.mode-switch');
    modeSwitch?.classList.toggle('active');
  }


  

  getItemsForUser(): Observable<any> {
    const username = localStorage.getItem('username');
    if (!username) {
      return of([]);
    }
    const url = `${this.baseurl}/formations/username/${username}`;
   
    return this.http.get(url);
  }


  

 //getData(){
   // this.listData = this.crudApi.getAll();
 // }


  getData(){
    this.listData = this.getItemsForUser();
   
    
  }

  removeData(id: number) {
    if (window.confirm('Are sure you want to delete this Catégorie ?')) {
    this.crudApi.deleteData(id)
      .subscribe(
        data => {
          console.log(data);
          this.toastr.success(' data successfully deleted!'); 
          this.getData();
        },
        error => console.log(error));
  }
  }
  selectData(item : Formation) {
    this.crudApi.choixmenu = "M";
    this.crudApi.dataForm = this.fb.group(Object.assign({},item));
    this.router.navigate(['user/formation'])
  }

  

}
