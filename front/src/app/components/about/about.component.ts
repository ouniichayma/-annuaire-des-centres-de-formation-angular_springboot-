import { Component, OnInit } from '@angular/core';
import { User } from 'src/app/model/user';
import { AuthService } from 'src/app/service/auth.service';
import { Observable } from 'rxjs/internal/Observable';
import { Router } from '@angular/router';
@Component({
  selector: 'app-about',
  templateUrl: './about.component.html',
  styleUrls: ['./about.component.css']
})
export class AboutComponent implements OnInit {
  user: User | null = null; // Change type to allow null values
  listData!: Observable<User[]>;
  p:number=1;
  constructor(public authService : AuthService,private route : Router ) { }

  ngOnInit(): void {
    this.getData();
    
  }
  getData() {
    this.listData = this.authService.getactiveUsers();
  }
  

  centredetails(id:number){
    this.route.navigate(['centredetails',id])
    
  }
  
}
