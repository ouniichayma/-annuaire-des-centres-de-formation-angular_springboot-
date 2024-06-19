import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { User } from 'src/app/model/user';
import { AuthService } from 'src/app/service/auth.service';

@Component({
  selector: 'app-centre-details',
  templateUrl: './centre-details.component.html',
  styleUrls: ['./centre-details.component.css']
})
export class CentreDetailsComponent implements OnInit {
  user:User=new User();
  id!:number;
  constructor(public authService:AuthService, private route : Router,private router:ActivatedRoute) { }

  ngOnInit(): void {
    this.id=this.router.snapshot.params[('id')];
    this.authService.getbyid(this.id).subscribe(data=>{
      this.user=data;
    
    })
  }


}
