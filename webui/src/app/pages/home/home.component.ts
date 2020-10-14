import { Component, OnInit } from '@angular/core';
import { Observable } from 'rxjs';
import { PostPayload } from '../add-post/post-payload';
import { PostService } from 'src/app/service/post.service';
import { CommentService } from 'src/app/service/comment.service';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {
  data = 'asdcqwecasdcqwecasdcqw  ecasdcqwecasdcqwecasdcqwecasdcqwec  asdcqwecas dcqwecasd cqweca sdcqwecasdcq';
  posts: Observable<Array<PostPayload>>;
  constructor(private postService: PostService,
    private commentservice:CommentService) { }

  ngOnInit() {
    this.posts = this.postService.getAllPosts();
  }


}
