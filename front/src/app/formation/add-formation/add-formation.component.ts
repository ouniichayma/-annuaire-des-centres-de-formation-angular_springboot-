import { Component, OnInit,Inject } from '@angular/core';
import { ScategorieService} from '../../service/scategorie.service';
import { CategorieService} from '../../service/categorie.service';
import { FormationService} from '../../service/formation.service';
import { ToastrService } from 'ngx-toastr';
import { FormBuilder, FormGroup, FormControl, ReactiveFormsModule,Validators }
from '@angular/forms';
import { Router } from '@angular/router';
import { Formation} from '../../model/formation';
import { Categorie} from '../../model/categorie';
import { Scategorie} from '../../model/scategorie';




import { EventEmitter, Output } from '@angular/core';
@Component({
  selector: 'app-add-formation',
  templateUrl: './add-formation.component.html',
  styleUrls: ['./add-formation.component.css']
})
export class AddFormationComponent implements OnInit {
 

 

  CategorieList!: Categorie[];
  ScategorieList: any;
  scategorie : any={};
  wcode : string = '';

  userFile:any; ;
  public imagePath: any;
  imgURL: any;
  public message!: string;
  file!: File;




  userId!: number;
  form!: FormGroup;
 


  
  
  constructor(public crudApi: FormationService ,public fb: FormBuilder,public toastr: ToastrService,
    public scategorieService: ScategorieService,
    public categorieService: CategorieService,
    private router : Router) { }
    
  ngOnInit(): void {
    if (this.crudApi.choixmenu == "A")
    {this.infoForm()};
    this.categorieService.getAll().subscribe(
      response =>{this.CategorieList = response;}
     );

     this.scategorieService.getAll().subscribe(
      response =>{this.ScategorieList = response;}
     );
   }



   infoForm() {
    this.crudApi.dataForm = this.fb.group({
        id: null,
        code: ['', [Validators.required]],
        libelle: ['', [Validators.required , Validators.pattern('^[a-zA-Z ]*$')]],
        username: ['', [Validators.required , Validators.pattern('^[a-zA-Z ]*$')]], 
        description: ['', [Validators.required ]], 

        pa: ['', [Validators.required ,  Validators.min(0),
          Validators.max(1000),
          Validators.pattern('^[0-9]+$')]],
       
        place: [0, [Validators.required]],
        num_cat: ['', [Validators.required]],
        num_scat: [0, [Validators.required]],
        local: ['', [Validators.required]],
        scategorie: this.fb.group({
          id: null,
     
      }),
      user:this.fb.group({
        id:null
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
  


onSelectCateg(event: Event) {
  console.log(event.target); // check the type of event.target
  const num_categ = (event.target as HTMLSelectElement).value;
  this.scategorieService.listScateg(num_categ).subscribe(
    response =>{this.ScategorieList = response;}
  );  
}





onSelectScateg(event: Event)
{
  console.log(event.target); // check the type of event.target
  const num_scat = (event.target as HTMLSelectElement).value;
 this.scategorieService.getData(num_scat).subscribe(
    response =>{
      this.scategorie = response;
      this.wcode = (10000 + this.scategorie.rang).toString().substring(1);
      this.wcode = this.wcode;
    this.crudApi.dataForm.controls['code'].setValue(this.wcode);
      }
   );  
} 










/*add() {
  this.crudApi.createData(this.crudApi.dataForm.value).
  subscribe( data => {
    alert("update rang");
    this.scategorieService.updateRang(this.crudApi.dataForm.value.num_scat,this.crudApi.dataForm.value).
    subscribe( data => {
      console.log('update rang');
    });
    this.toastr.success('validation faite avec Success');
  this.ResetForm();
  this.router.navigate(['/listformation']); 
  });
}*/







addData(){
  // subscribe to getUserIdByUsername() to get the user ID
  this.crudApi.getUserIdByUsername(this.crudApi.dataForm.value.username).subscribe(userId => {
    // set the user.id value to the retrieved user ID
    this.crudApi.dataForm.get('user.id')?.setValue(userId);
  // set the scategorie.id value to the same as num_cat value
  this.crudApi.dataForm.get('scategorie.id')?.setValue(this.crudApi.dataForm.value.num_scat);
  // call the createData() method of the service to add the new data
  const formdata=new FormData();
  const formation=this.crudApi.dataForm.value;
  formdata.append('formation',JSON.stringify(formation));
  formdata.append('file',this.userFile);
  this.crudApi.createData(formdata).subscribe(data =>{
    this.scategorieService.updateRang(this.crudApi.dataForm.value.num_scat,this.crudApi.dataForm.value).
    subscribe( data => {
      console.log('update rang');
    });
    this.toastr.success('validation faite avec Success');
    location.reload(); 
  });

});
}










  updateData()
  {
    this.crudApi.updatedata(this.crudApi.dataForm.value.id,this.crudApi.dataForm.value).
    subscribe( data => {
      this.toastr.success('modification faite avec succes');
    this.ResetForm();
    
   
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



  
  


}