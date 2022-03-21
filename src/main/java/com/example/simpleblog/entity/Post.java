package com.example.simpleblog.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@Entity
@Table(name = "posts")
public class Post implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String text;
    private Date date;
    @OneToOne
    private User user;

    @Transient
    private List<PostComment> postComments;
    @Transient
    private List<PostLike> likes;

    public Post(int id, User user, String text, Date date) {
        this.id = id;
        this.user = user;
        this.text = text;
        this.date = date;
    }
}
