package com.sanketh.AIChatBot.Security;

import com.sanketh.AIChatBot.Entity.User;
import com.sanketh.AIChatBot.Repository.UserRepository;
import org.springframework.data.jpa.repository.support.JpaRepositoryImplementation;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class UserServiceImpl extends UserDetailsService {
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    User user=    userRepository.findByUsername(username);
    }
}
