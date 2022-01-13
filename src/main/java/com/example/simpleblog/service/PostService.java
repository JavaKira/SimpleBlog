package com.example.simpleblog.service;

import com.example.simpleblog.entity.Post;
import com.example.simpleblog.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostService implements IPostService {
    @Autowired
    private PostRepository repository;

    @Override
    public Post getByID(int id) {
        return repository.getById(id);
    }

    @Override
    public List<Post> getAll() {
        return repository.findAll();
    }

    @Override
    public void add(Post data) {
        repository.save(data);
    }
}
