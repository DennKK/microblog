package com.boot.microblog.controllers;

import com.boot.microblog.domain.PostEntity;
import com.boot.microblog.repos.PostRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@Controller
public class PostController {
    @Autowired
    PostRepo postRepo;

    @GetMapping("/posts")
    public String allPosts(Map<String, Object> model) {
        Iterable<PostEntity> posts = postRepo.findAll();
        model.put("posts", posts);
        return "posts";
    }

    @PostMapping("/posts")
    public String addPost(@RequestParam String text, @RequestParam String tag, Map<String, Object> model) {
        PostEntity post = new PostEntity(text, tag);
        postRepo.save(post);

        Iterable<PostEntity> posts = postRepo.findAll();
        model.put("posts", posts);
        return "posts";
    }
}