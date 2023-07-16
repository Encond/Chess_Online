package org.project.chess_online.service;

import org.project.chess_online.entity.ERole;
import org.project.chess_online.entity.User;
import org.project.chess_online.repository.UserRepository;
import org.project.chess_online.security.request.SignupRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Set;

@Service
public class UserService implements UserDetailsService {
    private final UserRepository userRepository;

    private final BCryptPasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepository userRepository, BCryptPasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public List<User> getAll() {
        return this.userRepository.findAll();
    }

    public User findById(Long id) {
        return this.userRepository.findById(id).get(); //.orElseGet(User::new);
    }

    public User findUserByUsername(String username) {
        return userRepository.findUserByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Username not found with username = " + username));
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = this.findUserByUsername(username);

        return new org.springframework.security.core.userdetails.
                User(user.getUsername(), user.getPassword(), getAuthorities(user.getRoles()));
    }

    private Collection<? extends GrantedAuthority> getAuthorities(Set<ERole> roles) {
        return roles.stream().map(eRole -> new SimpleGrantedAuthority(eRole.name())).toList();
    }

    public User createUser(SignupRequest signupRequest) {
        User user = new User();
        user.setUsername(signupRequest.getUsername());
        user.setPassword(passwordEncoder.encode(signupRequest.getPassword()));
        user.getRoles().add(ERole.ROLE_USER);

        return userRepository.save(user);
    }
}
