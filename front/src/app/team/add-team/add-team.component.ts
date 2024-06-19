import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { ToastrService } from 'ngx-toastr';
import { TeamService } from 'src/app/service/team.service';

@Component({
  selector: 'app-add-team',
  templateUrl: './add-team.component.html',
  styleUrls: ['./add-team.component.css']
})
export class AddTeamComponent implements OnInit {
  userFile:any; ;
  public imagePath: any;
  imgURL: any;
  public message!: string;
  file!: File;




  userId!: number;
  form!: FormGroup;
  constructor(public crudApi: TeamService ,public fb: FormBuilder,public toastr: ToastrService,
  
    private router : Router) { }

  ngOnInit(): void {
    if (this.crudApi.choixmenu == "A")
    {this.infoForm()};
  }



  infoForm() {
    this.crudApi.dataForm = this.fb.group({
        id: null,
        code: ['', [Validators.required]],
        nom: ['', [Validators.required , Validators.pattern('^[a-zA-Z ]*$')]],
        prenom: ['', [Validators.required , Validators.pattern('^[a-zA-Z ]*$')]],
        username: ['', [Validators.required , Validators.pattern('^[a-zA-Z ]*$')]], 

       
      
        local: ['', [Validators.required]],
        role: ['', [Validators.required]],
     
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
    else(this.crudApi.choixmenu == "M")
    {
      
     this.updateData()
    }
   
}






addData() {
  // subscribe to getUserIdByUsername() to get the user ID
  this.crudApi.getUserIdByUsername(this.crudApi.dataForm.value.username).subscribe(userId => {
    // set the user.id value to the retrieved user ID
    this.crudApi.dataForm.get('user.id')?.setValue(userId);

    const formdata=new FormData();
    const team=this.crudApi.dataForm.value;
    formdata.append('team',JSON.stringify(team));
    formdata.append('file',this.userFile);
  this.crudApi.createData(formdata).
  subscribe( data => {
    this.toastr.success('validation faite avec Success');
  this.ResetForm();
 
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
