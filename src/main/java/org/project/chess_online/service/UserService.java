package org.project.chess_online.service;

import org.project.chess_online.entity.User;
import org.project.chess_online.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> getAll() {
        return this.userRepository.findAll();
    }

    public User findById(Long id) {
        return this.userRepository.findById(id).get(); //.orElseGet(User::new);
    }
}
