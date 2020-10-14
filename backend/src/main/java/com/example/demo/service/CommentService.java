package com.example.demo.service;

import com.example.demo.Entity.Comment;
import com.example.demo.dto.CommentDto;
import javassist.NotFoundException;

import java.util.List;

public interface CommentService {
    public List<CommentDto> findAllComment()  throws NotFoundException;

    public CommentDto findCommentId(Long id) throws NotFoundException ;

    public List<Comment> findByPost(String post)throws NotFoundException;

    public CommentDto saveComment(CommentDto commentDto) throws  Exception;

}
