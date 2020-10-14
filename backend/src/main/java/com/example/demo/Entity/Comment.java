package com.example.demo.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "comment")
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private Long id;

    @Type(type = "text")

    @Column(name = "yorum", length = 500)
    private String yorum;

    @NotNull
    @Column(name = "post")
    private String post;

 /*   @NotNull
    @JoinColumn(name = "user_id")
    @ManyToOne(optional = true, fetch = FetchType.LAZY)
    private User user;*/

    /*@JoinColumn(name = "post_id")
    @ManyToOne(optional = true, fetch = FetchType.LAZY)
    private Post post;*/


}
