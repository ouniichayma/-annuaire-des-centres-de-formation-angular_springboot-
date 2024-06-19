import { Component, OnInit } from '@angular/core';
import { FormationService} from '../../service/formation.service'

import { FormBuilder, FormGroup, FormControl, ReactiveFormsModule,Validators }

from '@angular/forms';
import { Router } from '@angular/router';
import { Formation} from '../../model/formation';
import { ToastrService } from 'ngx-toastr';
import { Observable, observable, of } from 'rxjs';
import { skip, take } from 'rxjs/operators';
import { PaginationInstance } from 'ngx-pagination';

@Component({
  selector: 'app-list-formation',
  templateUrl: './list-formation.component.html',
  styleUrls: ['./list-formation.component.css']
})
export class ListFormationComponent implements OnInit {
  formation!: Formation;
  
  control: FormControl = new FormControl('');
  listData!: Observable<Formation[]>;
  p: number = 1;
  currentPage: number = 1;
  itemsPerPage: number = 10;
  
  constructor(public crudApi: FormationService, public toastr: ToastrService,
    private router : Router,public fb: FormBuilder,) { }

    ngOnInit() {
      this.getData();



      
  }
  getData(){
    this.listData = this.crudApi.getAll();
  }

  


  removeData(id: number) {
    if (window.confirm('Are sure you want to delete this Formation ?')) {
    this.crudApi.deleteData(id)
      .subscribe(
        data => {
          console.log(data);
          this.toastr.success(' Formation successfully deleted!'); 
          this.getData();
        },
        error => console.log(error));
  }
  }
  selectData(item : Formation) {
    this.crudApi.choixmenu = "M";
    this.crudApi.dataForm = this.fb.group(Object.assign({},item));
    this.router.navigate(['admin/formation'])
  }


 

}
