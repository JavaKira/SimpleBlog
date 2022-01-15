package com.example.simpleblog.controller;

import com.example.simpleblog.entity.Post;
import com.example.simpleblog.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
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
        if (request.getParameter("editable_post_id") != null) {
            try {
                model.addAttribute("editable_post",
                        (Post) postService.getByID(Integer.parseInt(request.getParameter("editable_post_id"))));
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
        }

        model.addAttribute("posts", posts);
        model.addAttribute("authorized", request.getSession().getAttribute("authorized"));
        return "blog/news";
    }

    @PatchMapping
    public String rewritePost(HttpServletRequest request)
    {
        System.out.println(request.getParameter("editable_post_id"));
        if (request.getParameter("editable_post_id") != null)
        {
            try {
                Post editablePost = postService.getByID(Integer.parseInt(request.getParameter("editable_post_id")));
                editablePost.setText(request.getParameter("text"));
                postService.add(editablePost);
            } catch (NumberFormatException e)
            {
                e.printStackTrace();
            }
        }

        return "redirect:/blog";
    }

    @PostMapping
    public String writePost(HttpServletRequest request)
    {
        Post post = new Post();
        post.setText(request.getParameter("text"));
        postService.add(post);
        return "redirect:/blog";
    }
}
