package com.sanketh.AIChatBot.Service;

import com.sanketh.AIChatBot.Entity.User;
import com.sanketh.AIChatBot.Exception.UserNotFoundException;
import com.sanketh.AIChatBot.Repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserDetailsStorage userDetailsStorage;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder, UserDetailsStorage userDetailsStorage) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.userDetailsStorage = userDetailsStorage;
    }

    public User getUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }
//    public User getUserByName(String username){
//        return userRepository.findByUsername(username);
//    }
//    public User getUserByEmail(String email){
//        return userRepository.findByEmail(email);
//    }
//    public User getUserById(int id){
//        return userRepository.findById(id).orElse(null);
//    }

    public List<User> getAllUser() {
        return userRepository.findAll();
    }

    public User deleteUserById(Integer id) {
        User user = userRepository.findById(id).orElse(null);
        if (user != null) {
            userRepository.deleteById(id);
            return user;
        }
        return null;
    }

    public User deleteAccountById() {
        User user = userDetailsStorage.getCurrentUser();
        if (user != null) {
            userRepository.deleteById(user.getId());
        } else
            throw new UserNotFoundException("User not found");
    }
}
