import { Component, Inject, OnInit } from '@angular/core';
import { CategorieService} from '../../service/categorie.service'

import { FormBuilder, FormGroup, FormControl, ReactiveFormsModule,Validators }

from '@angular/forms';
import { Router } from '@angular/router';
import { Categorie} from '../../model/categorie';
import { ToastrService } from 'ngx-toastr';
@Component({
  selector: 'app-add-categorie',
  templateUrl: './add-categorie.component.html',
  styleUrls: ['./add-categorie.component.css']
})
export class AddCategorieComponent implements OnInit {

  userFile:any; ;
  public imagePath: any;
  imgURL: any;
  public message!: string;
  file!: File;




  userId!: number;
  form!: FormGroup;
 
  constructor(public crudApi: CategorieService ,public fb: FormBuilder,public toastr: ToastrService,
  
    private router : Router
    ) { }

  ngOnInit() {
  
    if (this.crudApi.choixmenu == "A")
    {this.infoForm()};
   }


  
  infoForm() {
    this.crudApi.dataForm = this.fb.group({
        id: null,
        code: ["", [Validators.required ,  Validators.min(0),
          Validators.max(1000),
          Validators.pattern('^[0-9]+$')]],
        libelle: ['', [Validators.required]],
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
  
   

/*addData() {
  this.crudApi.createData(this.crudApi.dataForm.value).
  subscribe( data => {
    this.toastr.success('validation faite avec Success');
  this.ResetForm();
  this.router.navigate(['/categorie']); 
  });
}*/







addData(){
  const formdata = new FormData();
  const Categorie = this.crudApi.dataForm.value;
  formdata.append('categorie', JSON.stringify(Categorie));
  formdata.append('file', this.userFile);
  this.crudApi.createData(formdata).subscribe(data => {
    this.toastr.success('Validation faite avec succès');
    location.reload(); 
  });
}



onSelectFile(event: Event) {
  const input = event.target as HTMLInputElement;
  if (input?.files && input.files.length > 0) {
    const file = input.files[0];
    this.userFile = file;
    const mimeType = file.type;
    if (!mimeType.match(/image\/*/)) {
      this.message = "Only images are supported.";
      return;
    }

    const reader = new FileReader();
    this.imagePath = file;
    reader.readAsDataURL(file);
    reader.onload = (_event) => {
      this.imgURL = reader.result;
    };
  }
}





  updateData()
  {
    this.crudApi.updatedata(this.crudApi.dataForm.value.id,this.crudApi.dataForm.value).
    subscribe( data => {
      this.toastr.success('modification faite avec succes');
    this.ResetForm();
    this.router.navigate(['/listcategorie']); 
   
    });
  }

}