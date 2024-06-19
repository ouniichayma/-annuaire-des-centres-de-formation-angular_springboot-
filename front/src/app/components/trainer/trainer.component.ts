import { Component, OnInit } from '@angular/core';
import { Observable, of } from 'rxjs';
import { Candidate } from 'src/app/model/candidate';
import { CandidateService } from 'src/app/service/candidate.service';

@Component({
  selector: 'app-trainer',
  templateUrl: './trainer.component.html',
  styleUrls: ['./trainer.component.css']
})
export class TrainerComponent implements OnInit {
  listcandidate!: Observable<Candidate[]>;
  pc: number = 1;
  constructor(public candidateService: CandidateService ) { }

  ngOnInit(): void {
    this.getDatacandidate()
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
