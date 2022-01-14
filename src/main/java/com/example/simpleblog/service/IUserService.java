package com.example.simpleblog.service;

import com.example.simpleblog.entity.User;

import java.util.List;

public interface IUserService {
    User getByID(int id);

    User getByLogin(String login);

    List<User> getAll();

    void add(User user);
}
