import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormControl } from '@angular/forms';
import { Router } from '@angular/router';
import { ToastrService } from 'ngx-toastr';
import { Observable, of } from 'rxjs';
import { Formation } from 'src/app/model/formation';
import { Team } from 'src/app/model/team';
import { FormationService } from 'src/app/service/formation.service';
import { HttpClient } from '@angular/common/http';
import { TeamService } from 'src/app/service/team.service';
@Component({
  selector: 'app-listteam',
  templateUrl: './listteam.component.html',
  styleUrls: ['./listteam.component.css']
})
export class ListteamComponent implements OnInit {
  team!: Team;
  
  control: FormControl = new FormControl('');
  listData!: Observable<Team[]>;
 
  baseurl = "http://localhost:8080/api";
  p: number = 1;
  currentPage: number = 1;
  itemsPerPage: number = 10;
  constructor(public crudApi: TeamService, public toastr: ToastrService,
    private router : Router,public fb: FormBuilder,private http:HttpClient) { }

  ngOnInit(): void {
    this.getData();
  }




  getItemsForUser(): Observable<any> {
    const username = localStorage.getItem('username');
    if (!username) {
      return of([]);
    }
    const url = `${this.baseurl}/teams/username/${username}`;
   
    return this.http.get(url);
  }


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
  selectData(item : Team) {
    this.crudApi.choixmenu = "M";
   
    this.crudApi.dataForm = this.fb.group(Object.assign({},item));
   
    this.router.navigate(['user/team'])
  }




}

