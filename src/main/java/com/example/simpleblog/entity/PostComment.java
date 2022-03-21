package com.example.simpleblog.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Data
@NoArgsConstructor
@Entity
@Table(name = "comments")
public class PostComment implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @OneToOne
    private User user;
    private int postId;
    private String text;

    public PostComment(int id, User user, int postId, String text) {
        this.id = id;
        this.user = user;
        this.postId = postId;
        this.text = text;
    }
}
