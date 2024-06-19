import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import axios from 'axios';
import { Observable } from 'rxjs/internal/Observable';
import { User } from 'src/app/model/user';
import { AuthService } from 'src/app/service/auth.service';
import { ToastrService } from 'ngx-toastr';
import {  observable } from 'rxjs';
import { FormBuilder, FormGroup } from '@angular/forms';
import { HttpClient } from '@angular/common/http';

@Component({
  selector: 'app-admin-dashboard',
  templateUrl: './admin-dashboard.component.html',
  styleUrls: ['./admin-dashboard.component.css']
})
export class AdminDashboardComponent implements OnInit {


  closeResult = '';
  data= [] 
  email : string = '';
  username : any;
  password : string = '';
  somme=[];
  active !:boolean;
  data2=[];
  somme1=[];
  users: User[] = [];
  
 
role: any;
admin: any;
searchType: string = ''; 
plog: User | null = null; // Change type to allow null values
user: User | null = null; // Change type to allow null values
listData!: Observable<User[]>;
inactiveUserCount: number = 0;
activeUserCount: number = 0;

searchForm!: FormGroup;
user$!: Observable<any>;
  p:number=1;
  constructor( public authService : AuthService ,private route : Router,public toastr: ToastrService,public fb: FormBuilder, private http: HttpClient) { 

  }

  ngOnInit(): void {
    this.searchForm = this.fb.group({
      username: [''],
      localisation: ['']
     
    });

    this.countInactiveUsers()
    this.countactiveUsers()
    this.getData();
    this.countall()
    this.searchByUsername()
    this.searchByLocalisation()
    this.username=localStorage.getItem('username');
    this.username=localStorage.getItem('role');
    this.username=localStorage.getItem('admin');
   
    const userId = localStorage.getItem('id');
  if (userId) {
    this.authService.getbyid(+userId).subscribe(plog => {
      this.plog = plog;
    });
  }
  




  }


  logout() {
    localStorage.removeItem("token");
    this.authService.logout();
    this.route.navigate(['']);
  }

countall(){
  axios.get('http://localhost:8080/auth/Alluser').then((res)=>this.somme=Object.values(res.data)).then((res)=>console.log(this.somme.length));
}



countInactiveUsers() {
  this.authService.countInactiveUsers().subscribe(
    (count: number) => {
      this.inactiveUserCount = count;
    },
    (error) => {
      console.log('Error fetching inactive user count:', error);
    }
  );
}

countactiveUsers() {
  this.authService.countactiveUsers().subscribe(
    (count: number) => {
      this.activeUserCount = count;
    },
    (error) => {
      console.log('Error fetching inactive user count:', error);
    }
  );
}


updateuser(id:number){
  this.route.navigate(['update',id])
  
}
loding(){
  this.authService.getAll()
  alert('deleted')
}


removeData(id: number) {
  if (window.confirm('Are sure you want to delete this user ?')) {
  this.authService.delet(id)
    .subscribe(
      data => {
        console.log(data);
        this.getData();
        this.toastr.success(' user successfully deleted!'); 
        
      },
      error => console.log(error));
}
}


/*searchByUsername() {
  if (this.searchType.trim() !== '') {
    this.user = null; // Clear previous search result
    this.users = []; // Clear previous search results
    this.authService.findByUsername(this.searchType).subscribe(
      (user: User) => {
        this.user = user;
        console.log(user);
      },
      (error) => {
        console.log('Error fetching user data', error);
      }
    );
  }
}*/



searchByUsername() {
  if (this.searchType.trim() !== '') {
    this.user = null; // Clear previous search result
    this.users = []; // Clear previous search results
    this.authService.findByUsername(this.searchType).subscribe(
      (result: any) => {
        if (Array.isArray(result)) { // Check if result is an array
          this.users = result;
        } else {
          this.user = result;
          this.users.push(result); // Add the user to the users array
        }
        console.log(result);
      },
      (error) => {
        console.log('Error fetching user data', error);
      }
    );
  }
}









searchByLocalisation() {
  if (this.searchType.trim() !== '') {
    this.user = null; // Clear previous search result
    this.users = []; // Clear previous search results
    this.authService.findByLocalisation(this.searchType).subscribe(
      (users: User[]) => {
        if (users.length > 0) {
          this.users = users;
        } else {
          console.log('No users found for this location');
        }
      },
      (error) => {
        console.log('Error fetching user data', error);
      }
    );
  }
}

searchinactive() {
  this.user = null; // Clear previous search result
  this.users = []; // Clear previous search results
  this.authService.getInactiveUsers().subscribe(
    (users: User[]) => {
      if (users.length > 0) {
        this.users = users;
      } else {
        console.log('No inactive users found');
      }
    },
    (error) => {
      console.log('Error fetching user data', error);
    }
  );
}

searchactive() {
  this.user = null; // Clear previous search result
  this.users = []; // Clear previous search results
  this.authService.getactiveUsers().subscribe(
    (users: User[]) => {
      if (users.length > 0) {
        this.users = users;
      } else {
        console.log('No inactive users found');
      }
    },
    (error) => {
      console.log('Error fetching user data', error);
    }
  );
}




getData() {
  this.listData = this.authService.getAll();
}





onSearch() {
  const { username, localisation} = this.searchForm.value;
  const params = { username, localisation };
  this.user$ = this.http.get('http://localhost:8080/auth/search', { params });
 console.log();
  
}






}
