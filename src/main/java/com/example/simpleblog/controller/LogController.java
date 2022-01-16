package com.example.simpleblog.controller;

import com.example.simpleblog.entity.User;
import com.example.simpleblog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/log")
public class LogController {
    @Autowired
    private UserService userService;

    @GetMapping
    private String showLoginPage()
    {
        return "log/login";
    }

    @PostMapping
    private String loginUser(HttpServletRequest request)
    {
        HttpSession session = request.getSession();
        User user = userService.getByLogin(request.getParameter("login"));
        if (!request.getParameter("password").equals(user.getPassword()))
            return "redirect:/log";

        session.setAttribute("user", user);
        return "redirect:/blog";
    }

    @PostMapping("/logout")
    private String logoutUser(HttpServletRequest request)
    {
        HttpSession session = request.getSession();
        session.setAttribute("user", null);
        return "redirect:/blog";
    }
}
