package com.sakinr.patika.airportreservationsystem.controller;

import com.sakinr.patika.airportreservationsystem.model.entity.User;
import com.sakinr.patika.airportreservationsystem.model.UserDataDTO;
import com.sakinr.patika.airportreservationsystem.model.UserLoginDTO;
import com.sakinr.patika.airportreservationsystem.model.UserResponseDTO;
import com.sakinr.patika.airportreservationsystem.service.iml.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@Validated
@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/signin")
    public String login(@Valid @RequestBody UserLoginDTO userLoginDTO) {
        return userService.signin(userLoginDTO.getUsername(), userLoginDTO.getPassword());
    }

    @PostMapping("/signup")
    public String signup(@RequestBody @Valid UserDataDTO user) {
        ModelMapper modelMapper = new ModelMapper();
        return userService.signup(modelMapper.map(user, User.class));
    }

    @DeleteMapping(value = "/{username}")
    public String delete(@PathVariable String username) {
        userService.delete(username);
        return username;
    }

    @GetMapping(value = "/me")
    public UserResponseDTO whoami(HttpServletRequest req) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(userService.whoami(req), UserResponseDTO.class);
    }

}
