package com.example.simpleblog.controller;

import com.example.simpleblog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("/log")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping
    private String showLoginPage()
    {
        return "user/login";
    }

    @GetMapping("/{id}")
    private String showUserPage(@PathVariable int id, Model model)
    {
        model.addAttribute("user", userService.getByID(id));
        return "user/user";
    }

    @PostMapping
    private String loginUser(Model model, HttpServletRequest request)
    {
        List<String> errors = userService.login(
                request.getParameter("login"),
                request.getParameter("password"),
                request.getSession()
        );
        model.addAttribute("errors", errors);
        return errors.size() == 0 ? "redirect:/blog" : "user/login";
    }

    @PostMapping("/logout")
    private String logoutUser(HttpServletRequest request)
    {
        userService.logout(request.getSession());
        return "redirect:/blog";
    }
}
