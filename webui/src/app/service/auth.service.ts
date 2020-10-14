import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { RegisterPayload } from '../auth/register-payload';
import { Observable } from 'rxjs';
import { LoginPayload } from '../auth/login-payload';
import { JwtAutResponse } from './jwt-aut-response';
import {map} from 'rxjs/operators';
import { LocalStorageService } from 'ngx-webstorage';
import { error } from '@angular/compiler/src/util';

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  private url = 'http://localhost:8182/api/auth/';
  constructor(private httpClient: HttpClient, private localStoraqeService: LocalStorageService) { }



  register(registerPayload: RegisterPayload): Observable<any> {
    return this.httpClient.post(this.url + 'signup', registerPayload);
  }

  login(loginPayload: LoginPayload): Observable<boolean> {
    return this.httpClient.post<JwtAutResponse>(this.url + 'login', loginPayload).pipe(map(data => {
      this.localStoraqeService.store('authenticationToken', data.authenticationToken);
      this.localStoraqeService.store('username', data.username);
      // console.log('key : ' + this.localStoraqeService.retrieve('authenticationToken'))
      // console.log('username : ' + this.localStoraqeService.retrieve('username'))
      return true;
    }));
  }

  isAuthenticated(): boolean {
    return this.localStoraqeService.retrieve('username') != null;
  }
  logout() {
    this.localStoraqeService.clear('authenticationToken');
    this.localStoraqeService.clear('username');
  }
}
