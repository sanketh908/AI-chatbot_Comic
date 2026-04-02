package com.sanketh.AIChatBot.Controller;


import com.sanketh.AIChatBot.DTO.SignupDTO;
import com.sanketh.AIChatBot.Entity.User;
import com.sanketh.AIChatBot.Enums.Roles;
import com.sanketh.AIChatBot.Service.UserService;
import io.swagger.v3.oas.annotations.tags.Tag;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@Tag(name = "AdminController", description = "Controller for handling admin operations such as managing users and accounts. Only users with the ROLE_ADMIN authority can access the endpoints in this controller.")
@RequestMapping("/admin")
public class AdminController {
    private final UserService userService;

    public AdminController(UserService userService) {
        this.userService = userService;
    }
    @GetMapping("/getAllUsers")
    public ResponseEntity<?> getAllUser(){
        List<User> userList= userService.getAllUser();

        if(!userList.isEmpty()){
            return new ResponseEntity<>(userList, HttpStatus.OK);
        }else
            return new ResponseEntity<>("No users found", HttpStatus.BAD_REQUEST);
    }
    @DeleteMapping("/deleteUser/{id}")
    public ResponseEntity<?> deleteUserById(@PathVariable Integer id){
        User user=userService.deleteUserById(id);
        if(user!=null){
            return ResponseEntity.ok().body("User with id "+id+" deleted successfully");
        }else
            return ResponseEntity.badRequest().body("User with id "+id+" not found");
    }
    @PostMapping("/addAdmin")
    public ResponseEntity<?> addAdmin(@RequestBody SignupDTO userDTO){
        User user=new User();
        user.setEmail(userDTO.getEmail());
        user.setPassword(userDTO.getPassword());
        user.setUsername(userDTO.getUsername());
        user.setRole(Roles.ROLE_ADMIN);
        User adminuser= userService.getUser(user);
        if(adminuser!=null){
           return new ResponseEntity<>(adminuser, HttpStatus.OK);
        }
        else
            return new ResponseEntity<>("User with id "+user.getId()+" not saved", HttpStatus.BAD_REQUEST);
    }
    @PostMapping("/deleteAccount")
    public ResponseEntity<?> deleteAccount(){
         userService.deleteAccountById();
        return new ResponseEntity<>("Account deleted successfully", HttpStatus.OK);

    }



}
