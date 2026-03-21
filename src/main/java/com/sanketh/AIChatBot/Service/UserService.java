package com.sanketh.AIChatBot.Service;

import com.sanketh.AIChatBot.Entity.User;
import com.sanketh.AIChatBot.Repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService {
private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    public User getUser(User user) {
       return   userRepository.save(user);
    }
    public User getUserByName(String username){
        return userRepository.findByUsername(username);
    }
    public User getUserByEmail(String email){
        return userRepository.findByEmail(email);
    }
    public User getUserById(int id){
        return userRepository.findById(id).
    }

}
