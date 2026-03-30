package com.sanketh.AIChatBot.Controller;

import com.sanketh.AIChatBot.Service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "*")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/deleteAccount")
    public ResponseEntity<?> deleteAccount(){
        userService.deleteAccountById();
        return new ResponseEntity<>("Account deleted successfully", HttpStatus.OK);

    }
}
