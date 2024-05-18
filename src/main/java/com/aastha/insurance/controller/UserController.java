package com.aastha.insurance.controller;

import com.aastha.insurance.dao.RoleRepository;
import com.aastha.insurance.entity.User;
import com.aastha.insurance.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/user")
public class UserController {

    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private RoleRepository roleRepository;

    @GetMapping("/details")
    public User getCurrentUser(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.isAuthenticated()) {
            String username = authentication.getName();
            User user = userService.findByEmail(username);
            return user;
        }
        return null;
    }
    @GetMapping("/isAdmin")
    public String isAdmin(){
        String result="false";
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.isAuthenticated()) {
            String username = authentication.getName();
            User user = userService.findByEmail(username);
            if (Objects.equals(user.getRoles().get(0).getName(), "ROLE_ADMIN")) {
                result = "true";
            }
        }
        return result;
    }

    @GetMapping("/all")
    public List<User> viewUsers(){
        return userService.findAll();
    }

    @PostMapping("/add")
    public List<User> addUser(@RequestBody User user){
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRoles(Arrays.asList(roleRepository.findByName("ROLE_USER")));
        userService.addUser(user);
        return userService.findAll();
    }

    @GetMapping("/get/{id}")
    public User viewUser(@PathVariable int id){
        if(userService.findUserById(id)==null){
            throw new RuntimeException("User with ID-"+id+" not found");
        }
        return userService.findUserById(id);
    }

    @PutMapping("/users")
    public List<User> updateUser(@RequestBody User user){
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRoles(Arrays.asList(roleRepository.findByName("ROLE_USER")));
        userService.updateUser(user);
        return userService.findAll();
    }

    @DeleteMapping("/users/{id}")
    public List<User> deleteUser(@PathVariable int id){
        if(userService.findUserById(id)==null){
            throw new RuntimeException("User with ID-"+id+" not found");
        }
        userService.deleteUser(id);
        return userService.findAll();
    }
}
