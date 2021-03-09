package com.samy.blog.myblog.domains;

import lombok.Data;
import lombok.Generated;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;

@Data
@Entity
public class Blog {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO )
    protected Long id;

    protected String title;

    protected Timestamp createdDate;

    @OneToMany(cascade = CascadeType.ALL, targetEntity = Comment.class)
    protected List<Comment> comments;

    protected int likes;

    protected int views;

    @OneToMany(cascade = CascadeType.ALL, targetEntity = Tag.class)
    protected List<Tag> tags;

    protected String content;

    protected  boolean status;

    protected String author;




}
