import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AboutComponent } from './components/about/about.component';
import { HomeComponent } from './components/home/home.component';
import { LoginComponent } from './auth/login/login.component';
import { SignupComponent } from './auth/signup/signup.component';
import { AdminDashboardComponent } from './component/admin-dashboard/admin-dashboard.component';
import { UserDashboardComponent } from './component/user-dashboard/user-dashboard.component';
import { SearchComponent } from './components/search/search.component';
import { UpdateComponent } from './crud/update/update.component';
import { AddCategorieComponent } from './categorie/add-categorie/add-categorie.component';
import { ListCategorieComponent } from './categorie/list-categorie/list-categorie.component';
import { ListScategorieComponent } from './scategorie/list-scategorie/list-scategorie.component';
import { AddScategorieComponent } from './scategorie/add-scategorie/add-scategorie.component';
import { ListFormationComponent } from './formation/list-formation/list-formation.component';
import { AddFormationComponent } from './formation/add-formation/add-formation.component';
import { ProfilComponent } from './profil/profil.component';
import { Userformation } from './model/userformation';
import { AdduserformationComponent } from './formation/adduserformation/adduserformation.component';
import { AuthGuard } from './service/guards/auth.guard';
import { AddTeamComponent } from './team/add-team/add-team.component';
import { ListteamComponent } from './team/listteam/listteam.component';
import { SignupclientComponent } from './auth/signupclient/signupclient.component';
import { LoginclientComponent } from './auth/loginclient/loginclient.component';
import { ClientdashboardComponent } from './component/clientdashboard/clientdashboard.component';
import { CoursesComponent } from './components/courses/courses.component';
import { NewsComponent } from './components/news/news.component';
import { CentreDetailsComponent } from './components/centre-details/centre-details.component';
import { CentreComponent } from './components/centre/centre.component';

const routes: Routes = [
  {path:'',component:HomeComponent},
  {path:'center',component:CentreComponent},
  {path:'about',component:AboutComponent},
  {path:'news',component:NewsComponent},
  {path:'search',component:SearchComponent},
  {path:'courses',component:CoursesComponent},
  {path:'login',component: LoginComponent},
  {path:'signup',component: SignupComponent},
  {path:'signupclient',component: SignupclientComponent},
  {path:'loginclient',component: LoginclientComponent},
  {path:'client',component: ClientdashboardComponent},
  {path:'admin',component: AdminDashboardComponent, canActivate:[AuthGuard]},
  {path:'user',component: UserDashboardComponent, canActivate:[AuthGuard]},
 
  {path:'user/listteam/team',component: AddTeamComponent},
  {path:'centredetails/:id',component: CentreDetailsComponent},
  {path:'update/:id',component: UpdateComponent},
  {path:'categorie',component: AddCategorieComponent},
  {path:'admin/listcategorie',component: ListCategorieComponent},
  {path:'admin/listscategorie',component: ListScategorieComponent},
  {path:'admin/listformation',component: ListFormationComponent},
  {path:'admin/formation',component: AddFormationComponent},
  {path:'user/formation',component: AddFormationComponent},
  {path:'admin/categorie',component: AddCategorieComponent},
  {path:'admin/scategorie',component: AddScategorieComponent},
  {path:'profil',component:ProfilComponent},
  {path:'user/profil',component: ProfilComponent},
  {path:'user/listteam',component: ListteamComponent},
  {path:'userformation',component: AdduserformationComponent}

];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
