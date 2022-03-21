package com.example.simpleblog.controller;

import com.example.simpleblog.entity.Post;
import com.example.simpleblog.entity.PostComment;
import com.example.simpleblog.entity.PostLike;
import com.example.simpleblog.entity.User;
import com.example.simpleblog.service.PostService;
import com.example.simpleblog.service.SessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

@Controller
@RequestMapping("/blog")
public class BlogController {
    @Autowired
    private PostService postService;
    @Autowired
    private SessionService sessionService;

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
        model.addAttribute("user", sessionService.getUser(request));
        return "blog/news";
    }

    @PatchMapping
    public String rewritePost(HttpServletRequest request)
    {
        User user = sessionService.getUser(request);
        if (request.getParameter("like_post_id") != null && user != null)
        {
            try {
                Post commentPost = postService.getByID(Integer.parseInt(request.getParameter("like_post_id")));
                var likes = commentPost.getLikes().stream().filter(like -> like.getUserId() == user.getId()).findAny();
                if (likes.isEmpty()) {
                    PostLike postLike = new PostLike();
                    postLike.setUserId(user.getId());
                    postLike.setPostId(commentPost.getId());
                    postService.saveLike(postLike);
                } else {
                    postService.deleteLike(likes.orElse(null));
                }
            } catch (NumberFormatException e)
            {
                e.printStackTrace();
            }
        }

        if (request.getParameter("comment_post_id") != null && user != null)
        {
            try {
                Post commentPost = postService.getByID(Integer.parseInt(request.getParameter("comment_post_id")));
                PostComment postComment = new PostComment();
                postComment.setUser(user);
                postComment.setText(request.getParameter("comment_text"));
                postComment.setPostId(commentPost.getId());
                postService.saveComment(postComment);
            } catch (NumberFormatException e)
            {
                e.printStackTrace();
            }
        }

        if (request.getParameter("editable_post_id") != null && user != null)
        {
            try {
                Post editablePost = postService.getByID(Integer.parseInt(request.getParameter("editable_post_id")));
                if (user.equals(editablePost.getUser())) {
                    editablePost.setText(request.getParameter("text"));
                    postService.add(editablePost);
                }
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
        User user = sessionService.getUser(request);
        if (request.getParameter("editable_post_id") != null && user != null)
        {
            try {
                Post editablePost = postService.getByID(Integer.parseInt(request.getParameter("editable_post_id")));
                if (user.equals(editablePost.getUser())) {
                    postService.delete(editablePost);
                }
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
        post.setUser(sessionService.getUser(request));
        post.setText(request.getParameter("text"));
        postService.add(post);
        return "redirect:/blog";
    }
}
