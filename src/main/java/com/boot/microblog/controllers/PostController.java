package com.boot.microblog.controllers;

import com.boot.microblog.domain.PostEntity;
import com.boot.microblog.repos.PostRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class PostController {
    @Autowired
    PostRepo postRepo;

    @GetMapping("/posts")
    public String showPosts(Model model) {
        Iterable<PostEntity> allPosts = postRepo.findAll();
        model.addAttribute("allPosts", allPosts);
        return "posts";
    }

    @PostMapping("/posts")
    public String addPost(@RequestParam String text, @RequestParam String tag, Model model) {
        PostEntity post = new PostEntity(text, tag);
        postRepo.save(post);

        Iterable<PostEntity> posts = postRepo.findAll();
        model.addAttribute("posts", posts);
        return "posts";
    }

    @PostMapping("/filter")
    public String filterPosts(@RequestParam String filter, Model model) {
        Iterable<PostEntity> posts;
        if (filter != null && !filter.isEmpty()) {
            posts = postRepo.findByTag(filter);
        } else {
            posts = postRepo.findAll();
        }

        model.addAttribute("posts", posts);
        return "posts";
    }
}