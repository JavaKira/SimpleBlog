package com.example.simpleblog.controller;

import com.example.simpleblog.entity.Post;
import com.example.simpleblog.entity.User;
import com.example.simpleblog.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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
        model.addAttribute("user", (User) request.getSession().getAttribute("user"));
        return "blog/news";
    }

    @PatchMapping
    public String rewritePost(HttpServletRequest request)
    {
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

    @DeleteMapping
    public String deletePost(HttpServletRequest request)
    {
        if (request.getParameter("editable_post_id") != null)
        {
            try {
                Post editablePost = postService.getByID(Integer.parseInt(request.getParameter("editable_post_id")));
                postService.delete(editablePost);
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
        post.setUserId(((User) request.getSession().getAttribute("user")).getId());
        post.setText(request.getParameter("text"));
        postService.add(post);
        return "redirect:/blog";
    }
}
