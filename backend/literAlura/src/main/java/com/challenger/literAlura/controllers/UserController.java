package com.challenger.literAlura.controllers;

import com.challenger.literAlura.dtos.UserDTO;
import com.challenger.literAlura.models.User;
import com.challenger.literAlura.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/{id}")
    public UserDTO getUserByID(@PathVariable Long id){
        System.out.println(id);
        return userService.findUserByID(id);
    }

}
