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
        return Optional.ofNullable(optionalUser).orElseThrow(() -> new UsernameNotFoundException("Username Not Found"))
            .map(UserDetailsImpl::new).get();
    }
}
//    @Autowired
//    private UserRepository userRepository;
//
//    public List<User> list() {
//        List<User> users = userRepository.findAll();
//
//        if (users.isEmpty())
//            return Collections.emptyList();
//        return users;
//    }
//
//    public Optional<User> getUser(Long userId) {
//        return Optional.ofNullable(userRepository.getOne(userId));
//    }
//
//    public void deleteUser(Long userId) {
//        userRepository.deleteById(userId);
//    }


