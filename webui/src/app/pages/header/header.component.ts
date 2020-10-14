import { Component, OnInit } from '@angular/core';
import { AuthService } from 'src/app/service/auth.service';
import { Router } from '@angular/router';
import { LocalStorageService } from 'ngx-webstorage';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent implements OnInit {
  controlAuth = false;
  constructor(private authService: AuthService, private router: Router ,
    private localStoraqeService:LocalStorageService) { }
  username;
  ngOnInit() {
    this.controlAuth = this.authService.isAuthenticated();
    console.log(this.controlAuth);
    this.username=this.localStoraqeService.retrieve('username')
  }
  logout() {
    this.authService.logout();
    this.router.navigateByUrl('/');
  }
}
