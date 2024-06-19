import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { AbstractControl, FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { Client } from 'src/app/model/client';
import { AuthclientService } from 'src/app/service/authclient.service';

@Component({
  selector: 'app-signupclient',
  templateUrl: './signupclient.component.html',
  styleUrls: ['./signupclient.component.css']
})
export class SignupclientComponent implements OnInit {
 
    id:number=0;
    username: string = '';
    prenom:string = '';
    email: string = '';
    num_tel:string = '';
    password: string = '';
  
    client : Client = new Client();

    signupclientForm!: FormGroup;

    userFile:any; ;
    public imagePath: any;
    imgURL: any;
    public message!: string;
    file!: File;



    current_fs!: HTMLElement;
    next_fs!: HTMLElement;
    previous_fs!: HTMLElement;
    animating = false;
  

  constructor(private authclientService : AuthclientService, private route : Router,private formBuilder: FormBuilder) { }

  ngOnInit(): void {
this.id;
this.username;
this.prenom;
this.email;
this.num_tel;
this.password;

function passwordMatchValidator(control: AbstractControl) {
  const password = control.get('password')?.value;
  const confirmPassword = control.get('confirmPassword')?.value;
  if (password !== confirmPassword) {
    control.get('confirmPassword')?.setErrors({ passwordMismatch: true });
    return { passwordMismatch: true };
  } else {
    return null;
  }
}


this.signupclientForm = this.formBuilder.group({
  id: [0, [Validators.required,]],
  num_tel: [0, [Validators.required,Validators.pattern('^(2|5|9|7|3)\\d{7}$')]],
  username: ['', [Validators.required, Validators.pattern('^[a-zA-Z ]*$')]],
  prenom: ['', [Validators.required, Validators.pattern('^[a-zA-Z ]*$')]],
  password: ['', [Validators.required,Validators.pattern('^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{8,}$')]],
  confirmPassword: ['', Validators.required],
  email: ['', [Validators.required,Validators.email]]
}, {
  validator: passwordMatchValidator
});




  }






  registerClient() {
    this.client = this.signupclientForm.value;
    
    
    
    const formData = new FormData();
    formData.append('client', JSON.stringify(this.client));
    formData.append('file', this.userFile);
    this.authclientService.signUp(formData).subscribe(res => {
      if (res.username == '' ||res.prenom=='' || res.email == '' || res.password == ''||res.num_tel==0) {
        alert('Registration failed');
        this.signupclientForm.reset();
      } else {
        console.log('Registration successful');
        alert('Registration successful');
        this.route.navigate(['/loginclient']);
      }
    }, err => {
      alert('Registration failed.');
      this.signupclientForm.reset();
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


  next() {
    if (this.animating) return;
    this.animating = true;
    this.current_fs = document.getElementById("fieldset1")!;
    this.next_fs = document.getElementById("fieldset2")!;
    this.next_fs.style.display = "block";
    this.current_fs.style.opacity = "0";
    let scale = 1 - (1 - parseFloat(this.current_fs.style.opacity)) * 0.2;
    let left = (parseFloat(this.current_fs.style.opacity) * 50) + "%";
    let opacity = 1 - parseFloat(this.current_fs.style.opacity);
    this.current_fs.style.transform = "scale(" + scale + ")";
    this.current_fs.style.position = "absolute";
    this.next_fs.style.left = left;
    this.next_fs.style.opacity = opacity.toString();
    setTimeout(() => {
      this.current_fs.style.display = "none";
      this.animating = false;
    }, 800);
  }
  next2() {
    if (this.animating) return;
    this.animating = true;
    this.current_fs = document.getElementById("fieldset2")!;
    this.next_fs = document.getElementById("fieldset3")!;
    this.next_fs.style.display = "block";
    this.current_fs.style.opacity = "0";
    let scale = 1 - (1 - parseFloat(this.current_fs.style.opacity)) * 0.2;
    let left = (parseFloat(this.current_fs.style.opacity) * 50) + "%";
    let opacity = 1 - parseFloat(this.current_fs.style.opacity);
    this.current_fs.style.transform = "scale(" + scale + ")";
    this.current_fs.style.position = "absolute";
    this.next_fs.style.left = left;
    this.next_fs.style.opacity = opacity.toString();
    setTimeout(() => {
      this.current_fs.style.display = "none";
      this.animating = false;
    }, 800);
  }

  previous(){
    if (this.animating) return;
    this.animating = true;
    this.current_fs = document.getElementById("fieldset2")!;
    this.previous_fs = document.getElementById("fieldset1")!;
    this.previous_fs.style.display = "block";
    this.current_fs.style.opacity = "0";
    let scale = 0.8 + (1 - parseFloat(this.current_fs.style.opacity)) * 0.2;
    let left = ((1 - parseFloat(this.current_fs.style.opacity)) * 50) + "%";
    let opacity = 1 - parseFloat(this.current_fs.style.opacity);
    this.current_fs.style.left = left;
    this.previous_fs.style.transform = "scale(" + scale + ")";
    this.previous_fs.style.opacity = opacity.toString();
    setTimeout(() => {
     
      this.animating = false;
    }, 800);
  }

  previous2(){
    if (this.animating) return;
    this.animating = true;
    this.current_fs = document.getElementById("fieldset3")!;
    this.previous_fs = document.getElementById("fieldset2")!;
    this.previous_fs.style.display = "block";
    this.current_fs.style.opacity = "0";
    let scale = 0.8 + (1 - parseFloat(this.current_fs.style.opacity)) * 0.2;
    let left = ((1 - parseFloat(this.current_fs.style.opacity)) * 50) + "%";
    let opacity = 1 - parseFloat(this.current_fs.style.opacity);
    this.current_fs.style.left = left;
    this.previous_fs.style.transform = "scale(" + scale + ")";
    this.previous_fs.style.opacity = opacity.toString();
    setTimeout(() => {
     
      this.animating = false;
    }, 800);
  }
}




