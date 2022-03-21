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

    private int userId;
    private int postId;
    private String text;

    public PostComment(int id, int userID, int postId, String text) {
        this.id = id;
        this.userId = userID;
        this.postId = postId;
        this.text = text;
    }
}
