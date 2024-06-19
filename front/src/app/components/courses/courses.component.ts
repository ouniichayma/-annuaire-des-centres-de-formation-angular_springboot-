import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormControl, FormGroup } from '@angular/forms';
import { Router } from '@angular/router';
import { ToastrService } from 'ngx-toastr';
import { Observable } from 'rxjs';
import { Formation } from 'src/app/model/formation';
import { FormationService } from 'src/app/service/formation.service';

@Component({
  selector: 'app-courses',
  templateUrl: './courses.component.html',
  styleUrls: ['./courses.component.css']
})
export class CoursesComponent implements OnInit {
  formation!: Formation;
  
  control: FormControl = new FormControl('');
  listData!: Observable<Formation[]>;
  p: number = 1;
  currentPage: number = 1;
  itemsPerPage: number = 10;



  searchForm!: FormGroup;
  formations$!: Observable<any>;
  constructor(public crudApi: FormationService, public toastr: ToastrService,
    private router : Router,public fb: FormBuilder, private http: HttpClient) { }

  ngOnInit(): void {
    this.getData();


    this.searchForm = this.fb.group({
      username: [''],
      local: [''],
      libelle: ['']
    });
  }

  getData(){
    this.listData = this.crudApi.getAll();
  }

  onSearch() {
    const { username, local, libelle } = this.searchForm.value;
    const params = { username, local, libelle };
    this.formations$ = this.http.get('http://localhost:8080/api/formations/search', { params });
   console.log();
    
  }




}
