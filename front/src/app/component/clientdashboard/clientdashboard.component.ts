import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { ToastrService } from 'ngx-toastr';
import { Observable } from 'rxjs';
import { Candidate } from 'src/app/model/candidate';
import { Client } from 'src/app/model/client';
import { Demande } from 'src/app/model/demande';
import { AuthclientService } from 'src/app/service/authclient.service';
import { CandidateService } from 'src/app/service/candidate.service';
import { DemandeService } from 'src/app/service/demande.service';

@Component({
  selector: 'app-clientdashboard',
  templateUrl: './clientdashboard.component.html',
  styleUrls: ['./clientdashboard.component.css']
})
export class ClientdashboardComponent implements OnInit {
  client: Client | null = null; // Change type to allow null values
  username : any;
  newDemande: Demande = new Demande();
  demandeForm!: FormGroup;


  selectedCV!: File;
  selectedPhoto!: File;

  candidate: Candidate = new Candidate();
  userFile!: string | Blob;
  listdemande!: Observable<Demande[]>;

  constructor(public authService : AuthclientService,private demandeService: DemandeService ,private route : Router,public toastr: ToastrService,public fb: FormBuilder, private http: HttpClient,private candidateService: CandidateService) { }

  ngOnInit(): void {


    this.username=localStorage.getItem('username');
    const clientId = localStorage.getItem('id');
    if (clientId) {
      this.authService.getbyid(+clientId).subscribe(client => {
        this.client = client;
      });
    } 












    


    this.demandeForm = this.fb.group({
      libelle: ['', [Validators.required , Validators.pattern('^[a-zA-Z ]*$')]],
      username: ['', [Validators.required , Validators.pattern('^[a-zA-Z ]*$')]], 
      description: ['', Validators.required],
      local: ['', Validators.required]
    });


   
  }

  addDemande() {
    this.demandeService.addDemande(this.newDemande)
      .subscribe(
        response => {
          console.log(response);
          this.toastr.success(' demande addet'); 
        },
        error => {
          console.log(error);
          this.toastr.error(' there are an error'); 
        }
      );
  }




  onSubmit() {
    const demande = new Demande();
    demande.libelle = this.demandeForm.get('libelle')!.value;
    demande.username = this.demandeForm.get('username')!.value;
    demande.description = this.demandeForm.get('description')!.value;
    demande.local = this.demandeForm.get('local')!.value;

    this.demandeService.addDemande(demande)
    .subscribe(
      (response: any) => console.log(response),
      (error: any) => console.error(error)
    );
    console.log(Error)
    location.reload(); 
  }







  onSubmitcandidat(): void {
    const formdata = new FormData();
    formdata.append('username', this.candidate.nom);
    formdata.append('prenom', this.candidate.prenom);
    formdata.append('secteur', this.candidate.secteur);
    formdata.append('cv', this.candidate.cv);
    formdata.append('photo', this.candidate.photo);
  
    this.candidateService.createCandidate(formdata)
      .subscribe(response => {
        console.log(response);
      }, error => {
        console.log(error);
      });
  }

 



  onFileChange(event: any, field: string) {
    if (event.target.files && event.target.files.length) {
      const file = event.target.files[0];
      this.candidate[field] = file;
    }
  }





  getData(){
    this.listdemande = this.demandeService.getAllDemandes();
  }





}
