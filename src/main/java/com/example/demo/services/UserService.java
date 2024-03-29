package com.example.demo.services;

import com.example.demo.models.User;
import com.example.demo.models.UserDetailsImpl;
import com.example.demo.repositories.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    private UserRepository usersRepository;

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        Optional<User> optionalUser = usersRepository.findByUsername(userName);
        if (optionalUser.isPresent()) {
            return new UserDetailsImpl(optionalUser.get());
        } else {
            throw new UsernameNotFoundException(String.format("User %s", userName));
        }
    }

    public void saveUser(User user) {
        usersRepository.save(user);
    }

    public boolean checkIfUserExists(String username) {
        return usersRepository.findByUsername(username).isPresent();

    }
}

