package com.example.simpleblog.entity;

import javax.persistence.*;
import java.io.Serializable;

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

    public PostComment() {
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int getPostId() {
        return postId;
    }

    public void setPostId(int postId) {
        this.postId = postId;
    }
}
