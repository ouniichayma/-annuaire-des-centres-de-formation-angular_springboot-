import { Component, OnInit } from '@angular/core';
import { CategorieService} from '../../service/categorie.service'

import { FormBuilder, FormGroup, FormControl, ReactiveFormsModule,Validators }

from '@angular/forms';
import { Router } from '@angular/router';
import { Categorie} from '../../model/categorie';
import { ToastrService } from 'ngx-toastr';
import { Observable, observable } from 'rxjs';
@Component({
  selector: 'app-list-categorie',
  templateUrl: './list-categorie.component.html',
  styleUrls: ['./list-categorie.component.css']
})
export class ListCategorieComponent implements OnInit {
  categorie!: Categorie;
  
  control: FormControl = new FormControl('');
  listData!: Observable<Categorie[]>;
  p:number=1;
  constructor(public crudApi: CategorieService, public toastr: ToastrService,
    private router : Router,public fb: FormBuilder,) { }

    ngOnInit() {
      this.getData();
  }
  getData(){
    this.listData = this.crudApi.getAll();
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
  selectData(item : Categorie) {
    this.crudApi.choixmenu = "M";
    this.crudApi.dataForm = this.fb.group(Object.assign({},item));
    this.router.navigate(['categorie'])
  }
}
