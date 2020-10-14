import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class CommentService {
    private url = 'http://localhost:8182/api/comment';

    constructor(private httpClient: HttpClient) { }

    addComment(comment):Observable<any>{
        return this.httpClient.post(this.url + '/add-comment', comment).pipe(map(
            res => {
                if (res) {
                  return res;
                } else {
                  return {};
                }
              }
        ));
      }

      getAllComment(): Observable<any>{
        return this.httpClient.get(this.url).pipe(map(
            res => {
                if (res) {
                  return res;
                } else {
                  return {};
                }
              }
        ));
      }

      getById(post: string): Observable<any> {
        return this.httpClient.get(this.url + '/findBy/' + post).pipe(map(
          res => {
            if (res) {
              return res;
            } else {
              return {};
            }
          }
        ));
      }

}
