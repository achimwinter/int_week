package com.example.demo.services;

import com.example.demo.models.User;
import com.example.demo.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public List<User> list() {
        List<User> users = userRepository.findAll();

        if (users.isEmpty())
            return Collections.emptyList();
        return users;
    }

    public Optional<User> getUser(Long userId) {
        return Optional.ofNullable(userRepository.getOne(userId));
    }

    public void deleteUser(Long userId) {
        userRepository.deleteById(userId);
    }

}
