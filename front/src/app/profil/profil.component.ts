import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { User } from '../model/user';
import { AuthService } from '../service/auth.service';

@Component({
  selector: 'app-profil',
  templateUrl: './profil.component.html',
  styleUrls: ['./profil.component.css']
})
export class ProfilComponent implements OnInit {
  id!:any;
  username!:any;
  user : User = new User;
  decodedPassword: string = '';
    constructor(public service:AuthService,private route:Router) { }
  
    ngOnInit(): void {
      this.id= localStorage.getItem('id');
      this.username= localStorage.getItem('username');
      this.service.getbyid(this.id).subscribe(data=>{
        this.user=data;
        this.decodedPassword = atob(data.password); // decode the password and store it
      })
  
    }
  
    
  update() {
    this.user.password = btoa(this.decodedPassword);
    this.service.update(this.user).subscribe(data => {
      this.route.navigate(['user'])
    })
  }

  onPasswordChange(event: any) {
    this.decodedPassword = event.target.value; // update the decoded password when the user enters a new password
  }

  
  }
  