package com.example.simpleblog.service;

import com.example.simpleblog.entity.Post;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface IPostService {
    Post getByID(int id);

    List<Post> getAll();

    void add(Post data);
}
