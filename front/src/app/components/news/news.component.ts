import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormControl, FormGroup } from '@angular/forms';
import { Router } from '@angular/router';
import { ToastrService } from 'ngx-toastr';
import { Observable } from 'rxjs';
import { Categorie } from 'src/app/model/categorie';
import { CategorieService } from 'src/app/service/categorie.service';
import { ScategorieService } from 'src/app/service/scategorie.service';

@Component({
  selector: 'app-news',
  templateUrl: './news.component.html',
  styleUrls: ['./news.component.css']
})
export class NewsComponent implements OnInit {
  categorie!: Categorie;
  
  control: FormControl = new FormControl('');
  listData!: Observable<Categorie[]>;
  ScategorieList: any;
  p:number=1;
  scategories: any[] = [];
  searchForm!: FormGroup;
  categ$!: Observable<any>;
  constructor(public crudApi: CategorieService, public toastr: ToastrService,
    private router : Router,public fb: FormBuilder,    public scategorieService: ScategorieService, private http: HttpClient) { }
  ngOnInit() {
    this.getData();

    
    this.searchForm = this.fb.group({
     
      libelle: ['']
    });
}
getData(){
  this.listData = this.crudApi.getAll();
}




loadScategories(num_cat: number) {
  this.scategorieService.listScate(num_cat).subscribe(data => {
    this.scategories = data;
    console.log(Response)
  });
}



/*loadScategories(event: Event) { 
   console.log(event.target); // check the type of event.target
const num_cat = (event.target as HTMLButtonElement).value;
  this.scategorieService.listScateg(num_cat).subscribe(response => {
    this.scategories = response;
  });
}*/



/*onclickCateg(event: Event) {
  console.log(event.target); // check the type of event.target
  const num_categ = (event.target as HTMLSelectElement).value;
  this.scategorieService.listScateg(num_categ).subscribe(
    response =>{this.ScategorieList = response;}
  );  
}*/







onSearch() {
  const {libelle } = this.searchForm.value;
  const params = {libelle };
  this.categ$ = this.http.get('http://localhost:8080/api/search', { params });
 console.log();
  
}

}
