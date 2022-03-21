package com.example.simpleblog.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@Entity
@Table(name = "likes")
public class PostLike {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private int userId;
    private int postId;

    public PostLike(int id, int userId, int postId) {
        this.id = id;
        this.userId = userId;
        this.postId = postId;
    }
}
