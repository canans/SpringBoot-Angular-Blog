package com.example.demo.Controller;

import com.example.demo.Entity.Comment;
import com.example.demo.dto.CommentDto;
import com.example.demo.dto.PostDto;
import com.example.demo.service.CommentService;
import com.example.demo.service.CommentServiceImp;
import com.example.demo.util.ApiPaths;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(ApiPaths.CommentCtrl.CTRL)
public class CommentController {
    @Autowired
    private CommentServiceImp commentService;

    @GetMapping()
    public ResponseEntity<List<CommentDto>> getAll() throws NotFoundException {
        List<CommentDto> commentDtos = commentService.findAllComment();
        return ResponseEntity.ok(commentDtos);
    }

    @PostMapping("/add-comment")
    public ResponseEntity<CommentDto> createComment(@Valid @RequestBody CommentDto commentDto) throws Exception {

        return ResponseEntity.ok(commentService.saveComment(commentDto));
    }
    @GetMapping("/findBy/{post}")
    public ResponseEntity<List<Comment>> getOne(@PathVariable(name = "post", required = true) String post)
            throws NotFoundException {
        return ResponseEntity.ok(commentService.findByPost(post));
    }

}
