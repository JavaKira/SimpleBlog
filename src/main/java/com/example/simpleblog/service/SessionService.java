package com.example.simpleblog.service;

import com.example.simpleblog.entity.User;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

@Service
public class SessionService {
    public User getUser(HttpServletRequest request)
    {
        return (User) request.getSession().getAttribute("user");
    }
}
