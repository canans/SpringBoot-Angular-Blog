package com.example.demo.repository;

import com.example.demo.Entity.Comment;
import com.example.demo.dto.CommentDto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment,Long> {
   List<Comment> findByPost(String post);
}
