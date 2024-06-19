import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { RouterModule,Routes } from '@angular/router';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { HeaderComponent } from './components/header/header.component';
import { FooterComponent } from './components/footer/footer.component';
import { SearchComponent } from './components/search/search.component';
import { Search2Component } from './components/search2/search2.component';
import { AboutComponent } from './components/about/about.component';
import { HomeComponent } from './components/home/home.component';
import { LoginComponent } from './auth/login/login.component';
import { SignupComponent } from './auth/signup/signup.component';
import { AdminDashboardComponent } from './component/admin-dashboard/admin-dashboard.component';
import { UserDashboardComponent } from './component/user-dashboard/user-dashboard.component';
import { UpdateComponent } from './crud/update/update.component';
import { AddCategorieComponent } from './categorie/add-categorie/add-categorie.component';
import { ListCategorieComponent } from './categorie/list-categorie/list-categorie.component';
import { ListScategorieComponent } from './scategorie/list-scategorie/list-scategorie.component';
import { AddScategorieComponent } from './scategorie/add-scategorie/add-scategorie.component';
import { AddFormationComponent } from './formation/add-formation/add-formation.component';
import { ListFormationComponent } from './formation/list-formation/list-formation.component';
import { FormsModule, ReactiveFormsModule,FormBuilder } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';

import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { ToastrModule } from 'ngx-toastr';
import { ProfilComponent } from './profil/profil.component';
import { AdduserformationComponent } from './formation/adduserformation/adduserformation.component';

import { NgxPaginationModule } from 'ngx-pagination';
import { AddTeamComponent } from './team/add-team/add-team.component';
import { ListteamComponent } from './team/listteam/listteam.component';
import { LoginclientComponent } from './auth/loginclient/loginclient.component';
import { SignupclientComponent } from './auth/signupclient/signupclient.component';
import { ClientdashboardComponent } from './component/clientdashboard/clientdashboard.component';
import { CoursesComponent } from './components/courses/courses.component';
import { NewsComponent } from './components/news/news.component';
import { CentreDetailsComponent } from './components/centre-details/centre-details.component';
import { CentreComponent } from './components/centre/centre.component';
import { TrainerComponent } from './components/trainer/trainer.component';


@NgModule({
  declarations: [
    AppComponent,
    HeaderComponent,
    FooterComponent,
    SearchComponent,
    Search2Component,
    AboutComponent,
    HomeComponent,
    LoginComponent,
    SignupComponent,
    AdminDashboardComponent,
    UserDashboardComponent,
    UpdateComponent,
    AddCategorieComponent,
    ListCategorieComponent,
    ListScategorieComponent,
    AddScategorieComponent,
    AddFormationComponent,
    ListFormationComponent,
    ProfilComponent,
    AdduserformationComponent,
    AddTeamComponent,
    ListteamComponent,
    LoginclientComponent,
    SignupclientComponent,
    ClientdashboardComponent,
    CoursesComponent,
    NewsComponent,
    CentreDetailsComponent,
    CentreComponent,
    TrainerComponent
  ],
  imports: [
   
    BrowserModule,
    AppRoutingModule,
    BrowserAnimationsModule,
    FormsModule,
    HttpClientModule, 
    ReactiveFormsModule, 
    BrowserAnimationsModule,
    ToastrModule.forRoot(),
    NgxPaginationModule,
    
    
    
   
     
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
