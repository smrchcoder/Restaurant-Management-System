package com.Arjunagi.ResturantManagementApp.controllers;

import com.Arjunagi.ResturantManagementApp.models.dto.AuthInpDto;
import com.Arjunagi.ResturantManagementApp.models.dto.LoginDto;
import com.Arjunagi.ResturantManagementApp.models.dto.UserRegDto;
import com.Arjunagi.ResturantManagementApp.models.user.User;
import com.Arjunagi.ResturantManagementApp.services.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController

public class UserController {
    @Autowired
    UserService userService;

    @PostMapping("/signup/npc/user")
    public String AddNPCUser(  @RequestBody @Valid UserRegDto userRegDto){
        return userService.AddNPCUser(userRegDto);
    }
    @PostMapping("/signup/admin")
    public String AddAdminUser( @RequestBody @Valid UserRegDto userRegDto){
        return userService.AddAdminUser(userRegDto);
    }
    @PostMapping("/signin")
    public AuthInpDto signInUser( @RequestBody @Valid LoginDto loginDto){
        return userService.signInUser(loginDto);
    }

    @DeleteMapping("/user/signout")
    private String logoutUser( @RequestBody AuthInpDto authInpDto){
        return userService.logoutUser(authInpDto);
    }

    @GetMapping("/user")
    public User getUser( @RequestBody AuthInpDto authInpDto){
        return userService.getUser(authInpDto);
    }

    @GetMapping("/users") //admin can only acess it
    public List<User> getAll( @RequestBody AuthInpDto authInpDto){
        return userService.getAll(authInpDto);
    }

    @DeleteMapping("/user/id/{id}/admin")
    public String deleteUserById( @RequestBody AuthInpDto authInpDto,@PathVariable Integer id){
        return userService.deleteUserById(authInpDto,id);
    }


}
