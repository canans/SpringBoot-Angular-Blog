package com.example.demo.service;

import com.example.demo.Entity.Comment;
import com.example.demo.dto.CommentDto;
import com.example.demo.repository.CommentRepository;
import com.example.demo.repository.PostRepository;
import javassist.NotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class CommentServiceImp implements CommentService{
    @Autowired
    private final CommentRepository commentRepository;
    private final ModelMapper modelMapper;
    private final PostRepository postRepository;

    public CommentServiceImp(CommentRepository commentRepository, ModelMapper modelMapper, PostRepository postRepository) {
        this.commentRepository = commentRepository;
        this.modelMapper = modelMapper;

        this.postRepository = postRepository;
    }

    @Override
    public List<CommentDto> findAllComment() throws NotFoundException {
        List<Comment> comments=commentRepository.findAll();
        CommentDto[] commentDtos=modelMapper.map(comments,CommentDto[].class);
        return Arrays.asList(commentDtos);
    }

    @Override
    public CommentDto findCommentId(Long id) throws NotFoundException {
        return null;
    }

    @Override
    public List<Comment> findByPost(String post) throws NotFoundException {

        List<Comment> comment = commentRepository.findByPost(post);

        return comment;
    }

    @Override
    public CommentDto saveComment(CommentDto commentDto) throws Exception {

        Comment comment=modelMapper.map(commentDto,Comment.class);

        commentRepository.save(comment);
        commentDto.setId(comment.getId());
       // commentDto.setPost_id(comment.getPost_id());
        return commentDto;
    }
}
