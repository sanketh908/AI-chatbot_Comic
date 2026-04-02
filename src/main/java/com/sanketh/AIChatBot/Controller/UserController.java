package com.sanketh.AIChatBot.Controller;

import com.sanketh.AIChatBot.Entity.User;
import com.sanketh.AIChatBot.Service.UserDetailsStorage;
import com.sanketh.AIChatBot.Service.UserService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
@CrossOrigin(origins = "*")
@Tag(name = "UserController", description = "Controller for handling user account management, including account deletion and username editing. All endpoints in this controller require authentication.")
public class UserController {
    private final UserService userService;
    private final UserDetailsStorage userDetailsStorage;

    public UserController(UserService userService, UserDetailsStorage userDetailsStorage) {
        this.userService = userService;
        this.userDetailsStorage = userDetailsStorage;
    }

    @PostMapping("/deleteAccount")
    public ResponseEntity<?> deleteAccount(){
        userService.deleteAccountById();
        return new ResponseEntity<>("Account deleted successfully", HttpStatus.OK);

    }
    @PostMapping("/Editusername")
    public ResponseEntity<?> editUsername(@RequestParam("username") String username){
            try {
                User user = userDetailsStorage.getCurrentUser();
                String oldUsername = user.getUsername();
                user.setUsername(username);
                return new ResponseEntity<>("user name updated  successfully from :"+oldUsername+"to :"+username, HttpStatus.OK);
            }catch (Exception e){
                return new ResponseEntity<>("Failed to update username", HttpStatus.INTERNAL_SERVER_ERROR);
            }

    }
}
