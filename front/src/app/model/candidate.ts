export class Candidate {
    id: number=0;
  nom: string = ''; 
  prenom: string = ''; 
  secteur: string = ''; 
  cv!: File;
  photo!: File ;
  [key: string]: any;

}
