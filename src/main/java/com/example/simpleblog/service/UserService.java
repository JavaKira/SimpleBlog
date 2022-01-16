package com.example.simpleblog.service;

import com.example.simpleblog.entity.User;
import com.example.simpleblog.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.List;

@Service
public class UserService implements IUserService {
    @Autowired
    private UserRepository repository;

    @Override
    public User getByID(int id) {
        return repository.getById(id);
    }

    @Override
    public User getByLogin(String login) {
        return getAll().stream().filter(user -> user.getLogin().equals(login)).findAny().orElse(null);
    }

    @Override
    public List<User> getAll() {
        return repository.findAll();
    }

    @Override
    public void add(User user) {
        repository.save(user);
    }

    @Override
    public boolean login(String login, String password, HttpSession session) {
        User user = getByLogin(login);
        if (!password.equals(user.getPassword()))
            return false;

        session.setAttribute("user", user);
        return true;
    }

    @Override
    public void logout(HttpSession session) {
        session.setAttribute("user", null);
    }

}
