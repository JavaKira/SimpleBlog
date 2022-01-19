package com.example.simpleblog.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "comments")
public class PostComment {
    @Id
    private Long id;

    private int userId;
    private int postId;
    private String text;

    public PostComment(Long id, int userID, int postId, String text) {
        this.id = id;
        this.userId = userID;
        this.postId = postId;
        this.text = text;
    }

    public PostComment() {
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
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
