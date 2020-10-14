import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { LoginComponent } from './auth/login/login.component';
import { RegisterComponent } from './auth/register/register.component';
import { AuthGuard } from './guard/auth.guard';
import { AddPostComponent } from './pages/add-post/add-post.component';
import { HomeComponent } from './pages/home/home.component';
import { PostComponent } from './pages/post/post.component';


const routes: Routes = [
    
    {path: '', component: HomeComponent},
    {path: 'register', component: RegisterComponent},
    {path: 'post/:id', component: PostComponent},
    {path: 'login', component: LoginComponent},
    {path: 'home', component: HomeComponent},
    {path: 'add-post', component: AddPostComponent , canActivate: [AuthGuard]},
]



@NgModule({

    imports: [RouterModule.forRoot(routes)],
    exports: [RouterModule]
  })
  export class AppRoutingModule {
  }