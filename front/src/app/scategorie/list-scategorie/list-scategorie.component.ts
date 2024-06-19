import { Component, OnInit } from '@angular/core';

import { FormBuilder, FormGroup, FormControl, ReactiveFormsModule,Validators }

from '@angular/forms';
import { Router } from '@angular/router';
import { ToastrService } from 'ngx-toastr';
import { Observable, observable } from 'rxjs';
import { Scategorie } from 'src/app/model/scategorie';
import { ScategorieService } from 'src/app/service/scategorie.service';
@Component({
  selector: 'app-list-scategorie',
  templateUrl: './list-scategorie.component.html',
  styleUrls: ['./list-scategorie.component.css']
})
export class ListScategorieComponent implements OnInit {
  scategorie!: Scategorie;
  
  control: FormControl = new FormControl('');
  listData!: Observable<Scategorie[]>;
  p:number=1;
  constructor(public crudApi: ScategorieService, public toastr: ToastrService,
    private router : Router,public fb: FormBuilder,) { }

    ngOnInit() {
      this.getData();
  }
  getData(){
    this.listData = this.crudApi.getAll();
  }

  removeData(id: number) {
    if (window.confirm('Are sure you want to delete this sous Catégorie ?')) {
    this.crudApi.deleteData(id)
      .subscribe(
        data => {
          console.log(data);
          this.toastr.success(' sous Catégorie successfully deleted!'); 
          this.getData();
        },
        error => console.log(error));
  }
  }
  selectData(item : Scategorie) {
    this.crudApi.choixmenu = "M";
    this.crudApi.dataForm = this.fb.group(Object.assign({},item));
    this.router.navigate(['admin/scategorie'])
  }
}
