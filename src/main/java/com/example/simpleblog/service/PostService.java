package com.example.simpleblog.service;

import com.example.simpleblog.entity.Post;
import com.example.simpleblog.entity.PostComment;
import com.example.simpleblog.entity.PostLike;
import com.example.simpleblog.repository.PostCommentRepository;
import com.example.simpleblog.repository.PostLikeRepository;
import com.example.simpleblog.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostService implements IPostService {
    @Autowired
    private PostRepository repository;
    @Autowired
    private PostCommentRepository commentRepository;
    @Autowired
    private PostLikeRepository likeRepository;

    @Override
    public Post getByID(int id) {
        Post post = repository.getById(id);
        post.setPostComments(getCommentsByPostId(post.getId()));
        post.setLikes(getLikesByPostId(post.getId()));
        return post;
    }

    @Override
    public List<PostComment> getCommentsByPostId(int id) {
        return commentRepository.findAll().stream().filter(postComment -> postComment.getPostId() == id).toList();
    }

    @Override
    public List<PostLike> getLikesByPostId(int id) {
        return likeRepository.findAll().stream().filter(like -> like.getPostId() == id).toList();
    }

    @Override
    public List<Post> getAll() {
        List<Post> posts = repository.findAll();
        posts.forEach(post -> {
            post.setLikes(getLikesByPostId(post.getId()));
            post.setPostComments(getCommentsByPostId(post.getId()));
        });
        return posts;
    }

    @Override
    public void add(Post data) {
        repository.save(data);
    }

    @Override
    public void saveComment(PostComment comment) {
        commentRepository.save(comment);
    }

    @Override
    public void saveLike(PostLike like) {
        likeRepository.save(like);
    }

    @Override
    public void delete(Post post) {
        repository.delete(post);
    }

    @Override
    public void deleteLike(PostLike like) {
        likeRepository.delete(like);
    }
}
