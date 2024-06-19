import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Client } from 'src/app/model/client';
import { AuthclientService } from 'src/app/service/authclient.service';

@Component({
  selector: 'app-loginclient',
  templateUrl: './loginclient.component.html',
  styleUrls: ['./loginclient.component.css']
})
export class LoginclientComponent implements OnInit {
  password : string = '';
 
 
  id!:number; 
  
  client : Client = new Client();
  
  roles : string='';
  username: string='';
  constructor(private authclientService : AuthclientService, private route : Router) { }

  ngOnInit(): void {
    this.username = '';
    this.password = '';
    
  }









  login() {
  
    this.client.username = this.username;
    this.client.password = this.password;
   
 
  
    this.authclientService.login(this.client).subscribe(res => {
      console.log(res)
  
      if(res == null) {
        alert("Uername or password is wrong");
   
        this.ngOnInit();
      }
     
      else {
        console.log("Login successful");
        localStorage.setItem("client",JSON.stringify(res));
        localStorage.setItem("token",res.token);
        localStorage.setItem("id",res.id);
        localStorage.setItem("username",res.username);
       
       
       
          this.route.navigate(['/client']);
        

      
  
      }
  
    },
    
    err => {
      
      alert("Login failed Or You've been rejected By admin");
      
      this.ngOnInit();
    })
  
  }









}
