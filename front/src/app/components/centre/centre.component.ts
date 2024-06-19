import { Component, OnInit } from '@angular/core';
import { User } from 'src/app/model/user';
import { AuthService } from 'src/app/service/auth.service';
import { Observable } from 'rxjs/internal/Observable';
import { Router } from '@angular/router';
import { HttpClient } from '@angular/common/http';
import { FormBuilder, FormGroup } from '@angular/forms';


@Component({
  selector: 'app-centre',
  templateUrl: './centre.component.html',
  styleUrls: ['./centre.component.css']
})
export class CentreComponent implements OnInit {
  user: User | null = null; // Change type to allow null values
  listData!: Observable<User[]>;
  p:number=1;
  searchForm!: FormGroup;
  user$!: Observable<any>;
  constructor(public authService : AuthService,private route : Router,public fb: FormBuilder, private http: HttpClient) { }

  ngOnInit(): void {
    this.getData();

    this.searchForm = this.fb.group({
      username: [''],
      localisation: [''],
      
    });
    
  }
  getData() {
    this.listData = this.authService.getactiveUsers();
  }
  

  centredetails(id:number){
    this.route.navigate(['centredetails',id])
    
  }

  onSearch() {
    const { username, localisation} = this.searchForm.value;
    const params = { username, localisation };
    this.user$ = this.http.get('http://localhost:8080/auth/search', { params });
   console.log();
    
  }
  
}
