package com.example.simpleblog.controller;

import com.example.simpleblog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

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
        return userService.login(
                request.getParameter("login"),
                request.getParameter("password"),
                request.getSession()
        ) ? "redirect:/log" : "redirect:/blog";
    }

    @PostMapping("/logout")
    private String logoutUser(HttpServletRequest request)
    {
        userService.logout(request.getSession());
        return "redirect:/blog";
    }
}
