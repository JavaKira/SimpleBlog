package com.example.simpleblog.service;

import com.example.simpleblog.entity.User;

import javax.servlet.http.HttpSession;
import java.util.List;

public interface IUserService {
    User getByID(int id);

    User getByLogin(String login);

    List<User> getAll();

    void add(User user);

    boolean login(String login, String password, HttpSession session);

    void logout(HttpSession session);
}
