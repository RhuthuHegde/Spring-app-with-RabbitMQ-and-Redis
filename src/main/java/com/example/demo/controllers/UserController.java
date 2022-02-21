package com.example.demo.controllers;

import com.example.demo.rabbitconfiguration.MessageConfiguration;
import com.example.demo.services.UserService;
import com.example.demo.usermodel.Users;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private RabbitTemplate template;

    @GetMapping("/new")
    public String display()
    {
        return "App with 2 datasource";
    }
    @GetMapping("/users")
    public ResponseEntity<List<Users>> getUsers()
    {
        List<Users> listUsers=userService.getAllUsers();
        return new ResponseEntity<>(listUsers, HttpStatus.OK);
    }
    @GetMapping("/users/{userId}")
    public ResponseEntity<String> getUsersbyId(@PathVariable Long userId)
    {
        Users user=userService.findByUserId(userId);
        return new ResponseEntity<>("The user is\n "+user,HttpStatus.OK);
    }
    @GetMapping("/users/count")
    public ResponseEntity<String> count()
    {
        Long val=userService.count();
        return new ResponseEntity<>("The number of users are "+val,HttpStatus.OK);
    }
    @PostMapping("/users/add")
    public ResponseEntity<String> addUsers(@RequestBody Users user)
    {
        Users userAdded=userService.saveUsers(user);
        template.convertAndSend(MessageConfiguration.EXCHANGE,MessageConfiguration.ROUTING_KEY2,userAdded);
        return new ResponseEntity<>("The user is added with the id "+userAdded.getUserId(),HttpStatus.CREATED);
    }
    @PutMapping("/users/update/{userId}")
    public ResponseEntity<String> changeUsers(@PathVariable Long userId, @RequestBody Users user)
    {
        Users userChanged= userService.updateUsers(userId,user);
        template.convertAndSend(MessageConfiguration.EXCHANGE,MessageConfiguration.ROUTING_KEY2,userChanged);
        return new ResponseEntity<>("The user with following id is updated with new values "+userId+"\n"+userChanged,HttpStatus.OK);
    }

    @DeleteMapping("/users/delete/{userId}")
    public ResponseEntity<String> deleteUsers(@PathVariable Long userId)
    {
      userService.deleteUsers(userId);
        return new ResponseEntity<>("The user with following ID has been deleted: "+userId,HttpStatus.OK);
    }
}
