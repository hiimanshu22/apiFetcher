package com.himanshu.apiFetcher.service;

import com.himanshu.apiFetcher.exception.ResourceNotFoundException;
import com.himanshu.apiFetcher.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.*;

@Service
public class UserService {
    private static final String USERS_URL = "https://jsonplaceholder.typicode.com/users";
    private Map<Integer, User> userCache = new HashMap<>();
    private final RestTemplate restTemplate = new RestTemplate();


    private void fetchUsers() {
        if (userCache.isEmpty()) {
            try {
                User[] users = restTemplate.getForObject(USERS_URL, User[].class);
                if (users != null) {
                    for (User user : users) {
                        userCache.put(user.getId(), user);
                    }
                }
            } catch (RestClientException e) {
                throw new RuntimeException("Failed to fetch users: " + e.getMessage());
            }
        }
    }

    public List<User> getAllUsers() {
        fetchUsers();
        return new ArrayList<>(userCache.values());
    }

    public User getUserById(int id) {
        fetchUsers();
        User user = userCache.get(id);
        if (user == null) throw new ResourceNotFoundException("User not found with id " + id);
        return user;
    }
}
