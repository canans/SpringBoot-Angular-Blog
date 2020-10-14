package com.example.demo.dto;

import com.example.demo.Entity.Post;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Id;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CommentDto {
    @Id
    private Long id;
    private String comment;
    private String post;

}
