package com.example.simpleblog.service;

import com.example.simpleblog.entity.Post;
import com.example.simpleblog.entity.PostComment;
import com.example.simpleblog.entity.PostLike;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface IPostService {
    Post getByID(int id);

    List<PostComment> getCommentsByPostId(int id);

    List<PostLike> getLikesByPostId(int id);

    List<Post> getAll();

    void add(Post data);

    void saveComment(PostComment comment);

    void saveLike(PostLike like);

    void delete(Post post);
}
