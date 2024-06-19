import { Component, OnInit } from '@angular/core';
import { Observable, of } from 'rxjs';
import { Candidate } from 'src/app/model/candidate';
import { Demande } from 'src/app/model/demande';
import { AuthService } from 'src/app/service/auth.service';
import { CategorieService } from 'src/app/service/categorie.service';
import { DemandeService } from 'src/app/service/demande.service';
import { CandidateService } from 'src/app/service/candidate.service';
import { FormationService } from 'src/app/service/formation.service';
import { ScategorieService } from 'src/app/service/scategorie.service';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {
 formationCount: number = 0;
 activeUserCount: number = 0;
 categorieCount: number = 0;
 demandeCount: number = 0; 
 scategorieCount: number = 0;
 listdemande!: Observable<Demande[]>;
 listcandidate!: Observable<Candidate[]>;
 p: number = 1;
 pa: number = 1;
 pc: number = 1;



  constructor( public formationService : FormationService,public scategorieService : ScategorieService,public categorieService : CategorieService,private demandeService: DemandeService ,public candidateService: CandidateService ,public authService : AuthService) { }

  ngOnInit(): void {
    this.countFormation()
    this.countactiveUsers()
    this.countCategorie()
    this.countDemande()
    this.countScategorie()
    this.getDatademande()
    this.getDatacandidate()
  }

  countFormation() {
    this.formationService.countFormations().subscribe(
      (count: number) => {
        this.formationCount = count;
      },
      (error) => {
        console.log('Error fetching formation count:', error);
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
  


  countCategorie() {
    this.categorieService.countCategories().subscribe(
      (count: number) => {
        this.categorieCount = count;
      },
      (error) => {
        console.log('Error fetching categorie count:', error);
      }
    );
  }



  countDemande() {
    this.demandeService.countDemandes().subscribe(
      (count: number) => {
        this.demandeCount = count;
      },
      (error) => {
        console.log('Error fetching demande count:', error);
      }
    );
  }






  countScategorie() {
    this.scategorieService.countScategories().subscribe(
      (count: number) => {
        this.scategorieCount = count;
      },
      (error) => {
        console.log('Error fetching categorie count:', error);
      }
    );
  }





  getDatademande() {
    console.log("getdemande() called");
    this.demandeService.getAllDemandes()
      .subscribe((demandes: Demande[]) => {
        console.log("getAllDemandes() subscription callback called");
        console.log("Demandes:", demandes);
        this.listdemande = of(demandes);
      }, (error) => {
        console.log("Error fetching demandes:", error);
      });
  }


  getDatacandidate() {
    console.log("getcandidate() called");
    this.candidateService.getAllcandidate()
      .subscribe((candidate: Candidate[]) => {
        console.log("getAllDemandes() subscription callback called");
        console.log("candidate:", candidate);
        this.listcandidate = of(candidate);
      }, (error) => {
        console.log("Error fetching demandes:", error);
      });
  }


}
