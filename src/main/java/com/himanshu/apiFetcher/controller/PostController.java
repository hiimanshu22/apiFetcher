package com.himanshu.apiFetcher.controller;

import com.himanshu.apiFetcher.model.Post;
import com.himanshu.apiFetcher.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/posts")
public class PostController {

    @Autowired
    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    // List posts (optional filter by userId)
    @GetMapping
    public List<Post> getPosts(@RequestParam Optional<Integer> userId) {
        return postService.getAllPosts(userId);
    }

    // Get single post by ID
    @GetMapping("/{id}")
    public Post getPostById(@PathVariable int id) {
        return postService.getPostById(id);
    }
}
