package com.example.simpleblog.controller;

import com.example.simpleblog.entity.Post;
import com.example.simpleblog.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.Collections;
import java.util.List;

@Controller
@RequestMapping("/blog")
public class BlogController {
    @Autowired
    private PostService postService;

    @GetMapping
    public String showPosts(Model model, HttpServletRequest request)
    {
        List<Post> posts = postService.getAll();
        Collections.reverse(posts);
        model.addAttribute("posts", posts);
        model.addAttribute("authorized", request.getSession().getAttribute("authorized"));
        return "blog/news";
    }
}
