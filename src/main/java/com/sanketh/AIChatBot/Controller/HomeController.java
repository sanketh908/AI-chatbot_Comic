package com.sanketh.AIChatBot.Controller;

import com.sanketh.AIChatBot.DTO.LoginDTO;
import com.sanketh.AIChatBot.DTO.Response;
import com.sanketh.AIChatBot.DTO.SignupDTO;
import com.sanketh.AIChatBot.Entity.User;
import com.sanketh.AIChatBot.Enums.Roles;
import com.sanketh.AIChatBot.Exception.ChatResponseGenerationException;
import com.sanketh.AIChatBot.Security.UserServiceImpl;
import com.sanketh.AIChatBot.Service.ChatService;
import com.sanketh.AIChatBot.Service.ResetTokenService;
import com.sanketh.AIChatBot.Service.UserService;
import com.sanketh.AIChatBot.Utilis.JWTUtilizer;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;



@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/home")
@Tag(name = "HomeController", description = "Controller for handling home page, user registration, login, password reset, and chatbot interactions. it don't  need authentication to access the endpoints in this controller")
public class HomeController {
    private  final   ChatService chatService;
    private final ResetTokenService resetTokenService;
    private final UserService userService;
    private final AuthenticationManager authenticationManager;
    private final JWTUtilizer jwtUtilizer;
    private final UserServiceImpl userServiceImpl;
    public HomeController(ChatService chatService, ResetTokenService resetTokenService, UserService userService, AuthenticationManager authenticationManager, JWTUtilizer jwtUtilizer, UserServiceImpl userServiceImpl) {
        this.chatService = chatService;
        this.resetTokenService = resetTokenService;
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
    public ResponseEntity<String> register(@Valid @RequestBody SignupDTO userDTO) {
        User user = new User();
        user.setUsername(userDTO.getUsername());
        user.setPassword(userDTO.getPassword());
        user.setEmail(userDTO.getEmail());
        user.setRole(Roles.ROLE_USER);
        User newuser=userService.getUser(user);
        if(newuser !=null){
            return new ResponseEntity<>("User signup successful Username : "+newuser.getUsername()+"Email :"+newuser.getEmail(), HttpStatus.OK);
        }else
            return new ResponseEntity<>("user signup failed !!",HttpStatus.BAD_REQUEST);

    }
    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginDTO userDTO) {
        try {
           Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(userDTO.getEmail(), userDTO.getPassword())
            );
            UserDetails userDetails=userServiceImpl.loadUserByUsername(userDTO.getEmail());
            String token = jwtUtilizer.generateToken(userDetails.getUsername());
            return new ResponseEntity<>(token, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Email or password is incorrect ",HttpStatus.BAD_REQUEST);
        }
    }
    @PostMapping("/forgot-password")
    public ResponseEntity<String> forgotPassword(@RequestParam("email")  String email) {
        User foundUser = userService.findUserByEmail(email);
        resetTokenService.sendMail(foundUser);
        return new ResponseEntity<>("Password reset token sent to email", HttpStatus.OK);


    }
    @PostMapping("/reset-password")
    public ResponseEntity<String> resetPassword(@RequestParam("token") String token, @RequestParam("newPassword") String newPassword) {
        try {
            resetTokenService.resetPassword(token, newPassword);
            return new ResponseEntity<>("Password reset successful", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
    @GetMapping("/chat")
    public ResponseEntity<Response> chat(@RequestParam("prompt") String prompt) {
        Response response = chatService.noLogInChat(prompt);
        if (response != null) {
            return new ResponseEntity<>(response, HttpStatus.OK);
        }
        else
            throw new ChatResponseGenerationException("Failed to generate response");
    }
    @GetMapping("/ping")
    public ResponseEntity<String> ping() {
        return new ResponseEntity<>("Online",HttpStatus.OK);
    }


}
