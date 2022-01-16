package com.example.simpleblog.service;

import com.example.simpleblog.entity.User;
import com.example.simpleblog.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

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
    public List<String> login(String login, String password, HttpSession session) {
        List<String> errors = new ArrayList<>();
        User user = getByLogin(login);
        if (user == null)
            errors.add("login");

        if (user != null && !password.equals(user.getPassword()))
            errors.add("password");

        if (errors.size() == 0)
        session.setAttribute("user", user);

        return errors;
    }

    @Override
    public void logout(HttpSession session) {
        session.setAttribute("user", null);
    }

}
