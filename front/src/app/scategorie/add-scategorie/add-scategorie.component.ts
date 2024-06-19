import { Component, Inject, OnInit } from '@angular/core';

import { FormBuilder, FormGroup, FormControl, ReactiveFormsModule,Validators }

from '@angular/forms';
import { Router } from '@angular/router';

import { ToastrService } from 'ngx-toastr';
import { Categorie } from 'src/app/model/categorie';
import { CategorieService } from 'src/app/service/categorie.service';
import { ScategorieService } from 'src/app/service/scategorie.service';

@Component({
  selector: 'app-add-scategorie',
  templateUrl: './add-scategorie.component.html',
  styleUrls: ['./add-scategorie.component.css']
})
export class AddScategorieComponent implements OnInit {
  CategorieList!: Categorie[];
  constructor(public crudApi: ScategorieService ,public fb: FormBuilder,public toastr: ToastrService,
    public categorieService: CategorieService,
    private router : Router
    ) { }

  ngOnInit() {
  
    if (this.crudApi.choixmenu == "A")
    {this.infoForm()};
    this.categorieService.getAll().subscribe(
      response =>{this.CategorieList = response;}
     );
   }


  
  infoForm() {
    this.crudApi.dataForm = this.fb.group({
        id: null,
        code: ["", [Validators.required ,  Validators.min(0),
          Validators.max(1000),
          Validators.pattern('^[0-9]+$')]],
        num_cat: ['', [Validators.required]],
        libelle: ['', [Validators.required]],
        categorie: this.fb.group({
          id: null
      })
      });
    }
   
  

  ResetForm() {
      this.crudApi.dataForm.reset();
  }
  onSubmit() {
   
    if (this.crudApi.choixmenu == "A")
    {
      this.addData();
    }
    else
    {
      
     this.updateData()
    }
   
}
  
   

addData() {
   // set the categorie.id value to the same as num_cat value
   this.crudApi.dataForm.get('categorie.id')?.setValue(this.crudApi.dataForm.value.num_cat);
   // call the createData() method of the service to add the new data
  this.crudApi.createData(this.crudApi.dataForm.value).
  subscribe( data => {
    this.toastr.success('validation faite avec Success');
    
  this.ResetForm();
  this.router.navigate(['/scategorie']); 
  location.reload(); 
  });
}








  updateData()
  {
     // set the categorie.id value to the same as num_cat value
   this.crudApi.dataForm.get('categorie.id')?.setValue(this.crudApi.dataForm.value.num_cat);
   // call the updateData() method of the service to modify the new data
    this.crudApi.updatedata(this.crudApi.dataForm.value.id,this.crudApi.dataForm.value).
    subscribe( data => {
      this.toastr.success('modification faite avec succes');
    this.ResetForm();
    this.router.navigate(['/listscategorie']); 
    location.reload(); 
   
    });
  }

}