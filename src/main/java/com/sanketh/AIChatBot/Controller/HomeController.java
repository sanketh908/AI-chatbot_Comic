package com.sanketh.AIChatBot.Controller;

import com.sanketh.AIChatBot.Entity.User;
import com.sanketh.AIChatBot.Enums.Roles;
import com.sanketh.AIChatBot.Security.UserServiceImpl;
import com.sanketh.AIChatBot.Service.UserService;
import com.sanketh.AIChatBot.Utilis.JWTUtilizer;
import org.springframework.boot.autoconfigure.neo4j.Neo4jProperties;
import org.springframework.boot.autoconfigure.pulsar.PulsarProperties;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;


@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/home")
public class HomeController {

    private final UserService userService;
    private final AuthenticationManager authenticationManager;
    private final JWTUtilizer jwtUtilizer;
    private final UserServiceImpl userServiceImpl;
    public HomeController(UserService userService, AuthenticationManager authenticationManager, JWTUtilizer jwtUtilizer, UserServiceImpl userServiceImpl) {

        this.userService = userService;
        this.authenticationManager = authenticationManager;
        this.jwtUtilizer = jwtUtilizer;
        this.userServiceImpl = userServiceImpl;
    }

    @GetMapping("/")
    public String home() {
        return "Welcome to the AI Chatbot !";
    }
    @GetMapping("/about")
    public String about() {
        return "this is a simple AI chatbot built using Spring Boot and " +
                "Ollama local module. It can respond to user queries and provide information " +
                "on various topics.try it our ";
    }
    @PostMapping("/signup")
    public ResponseEntity<User> register(@RequestBody  User user) {
        user.setRole(Roles.ROLE_USER);
        User newuser=userService.getUser(user);
        if(newuser !=null){
            return new ResponseEntity<>(newuser, HttpStatus.OK);
        }else
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

    }
    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody  User user) {
        try {
           Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(user.getEmail(), user.getPassword())
            );
            UserDetails userDetails=userServiceImpl.loadUserByUsername(user.getUsername());
            String token = jwtUtilizer.generateToken(userDetails.getUsername());
            return new ResponseEntity<>(token, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

}
