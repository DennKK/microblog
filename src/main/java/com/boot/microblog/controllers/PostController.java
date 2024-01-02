package com.boot.microblog.controllers;

import com.boot.microblog.domain.PostEntity;
import com.boot.microblog.repos.PostRepo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class PostController {
    private final PostRepo postRepo;

    public PostController(PostRepo postRepo) {
        this.postRepo = postRepo;
    }

    @GetMapping("/posts")
    public String showForm(Model model) {
        model.addAttribute("newPost", new PostEntity());
        Iterable<PostEntity> allPosts = postRepo.findAll();
        model.addAttribute("allPosts", allPosts);
        return "posts";
    }

    @PostMapping("/posts")
    public String addPost(@ModelAttribute PostEntity post, Model model) {
        postRepo.save(post);
        model.addAttribute("newPost", new PostEntity());
        Iterable<PostEntity> allPosts = postRepo.findAll();
        model.addAttribute("allPosts", allPosts);
        return "posts";
    }

    @PostMapping("/filter")
    public String filterPosts(@RequestParam String tag, Model model) {
        Iterable<PostEntity> allPosts;
        if (tag != null && !tag.isEmpty()) {
            allPosts = postRepo.findByTag(tag);
        } else {
            allPosts = postRepo.findAll();
        }

        model.addAttribute("newPost", new PostEntity());
        model.addAttribute("allPosts", allPosts);
        return "posts";
    }
}