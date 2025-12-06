package com.himanshu.apiFetcher.service;

import com.himanshu.apiFetcher.exception.ResourceNotFoundException;
import com.himanshu.apiFetcher.model.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class PostService {
    private final String POSTS_URL = "https://jsonplaceholder.typicode.com/posts";
    private Map<Integer, Post> postCache = new HashMap<>();

    private final RestTemplate restTemplate = new RestTemplate();


    private void fetchPosts() {
        if (postCache.isEmpty()) {
            try {
                Post[] posts = restTemplate.getForObject(POSTS_URL, Post[].class);
                if (posts != null) {
                    for (Post post : posts) {
                        postCache.put(post.getId(), post);
                    }
                }
            } catch (RestClientException e) {
                throw new RuntimeException("Failed to fetch posts: " + e.getMessage());
            }
        }
    }

    public List<Post> getAllPosts(Optional<Integer> userId) {
        fetchPosts();
        if (userId.isPresent()) {
            return postCache.values().stream()
                    .filter(p -> p.getUserId() == userId.get())
                    .collect(Collectors.toList());
        }
        return new ArrayList<>(postCache.values());
    }

    public Post getPostById(int id) {
        fetchPosts();
        Post post = postCache.get(id);
        if (post == null) throw new ResourceNotFoundException("Post not found with id " + id);
        return post;
    }
}
