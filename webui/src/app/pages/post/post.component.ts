import { Component, OnInit } from '@angular/core';
import { PostPayload } from '../add-post/post-payload';
import { ActivatedRoute } from '@angular/router';
import { PostService } from 'src/app/service/post.service';
import { AuthService } from 'src/app/service/auth.service';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { CommentService } from 'src/app/service/comment.service';
import { Comments } from './comment';
import { LocalStorageService } from 'ngx-webstorage';

@Component({
  selector: 'app-post',
  templateUrl: './post.component.html',
  styleUrls: ['./post.component.css']
})
export class PostComponent implements OnInit {
  post: PostPayload;
  permaLink: number;
  postid:string;
  controlAuth = false;
  CommentForm:FormGroup;
  comment:Comments;
  comments:any[]=[];
  username;
  constructor(
    private router: ActivatedRoute, 
    private postService: PostService,
    private authService: AuthService,
    private fb:FormBuilder,
    private commentService:CommentService,
    private localStoraqeService: LocalStorageService) { }

  ngOnInit() {
    this.CommentForm= this.fb.group({
     
      comment:['',Validators.required],
     
    });
    this.comment = {   
         
      comment:'',
      post:null
    };
  
    this.router.params.subscribe(params => {
      this.permaLink = params['id'];
      this.postid=JSON.parse(params['id']);
      console.log(this.postid)
      this.controlAuth = this.authService.isAuthenticated();
      this.username=this.localStoraqeService.retrieve('username')
      console.log(this.username)
      console.log(this.controlAuth);
    });

    this.postService.getPost(this.permaLink).subscribe(data => {
      this.post = data;
      
    }, (err: any) => {
      console.log('Failure Response');
    })
    

    
    this.getComment();
  }

  
  onSubmit(){
    if (this.CommentForm.invalid) {
      return;

  }
  
  }

  createComment(){
    
    this.comment.post=JSON.stringify(this.postid)
    this.comment.comment=this.CommentForm.get('comment').value;

    console.log(this.comment)

    this.commentService.addComment(this.comment).subscribe(
      data=>{
       console.log("success")
      },
      Error=>{
        console.log(Error)
      }
    )
  }
  getComment(){

    this.commentService.getById(this.postid).subscribe(
      data=>{
        this.comments=data;
        console.log(data)

      }
    )
  }
  getAllComment(){
    this.commentService.getAllComment().subscribe(
      data=>{
        console.log(data)
       
      },Error=>
      console.log(Error)
    )
  }

}
