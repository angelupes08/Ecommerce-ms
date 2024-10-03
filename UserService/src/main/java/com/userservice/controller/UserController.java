package com.userservice.controller;

import com.userservice.dtos.AuthRequest;
import com.userservice.dtos.RegisterRequest;
import com.userservice.dtos.UserDto;
import com.userservice.model.Users;
import com.userservice.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
//usercontroller
public class UserController {

    @Autowired
    AuthService authService;

    @Autowired
    AuthenticationManager manager;

    @PostMapping("/register")
    public ResponseEntity<RegisterRequest> registerUser(@RequestBody RegisterRequest registerRequest){

        return new ResponseEntity<>(authService.register(registerRequest), HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody AuthRequest request){

        this.doAuthenticate(request.getEmail(), request.getPassword());

        return new ResponseEntity<>(authService.generateToken(request),HttpStatus.OK);
    }

    @GetMapping("/userInfo")
    public UserDto getLoggedInUser(@RequestHeader("Authorization") String token) {

        token = token.replace("Bearer ", "");

        return authService.getUserInfo(token);
    }

    private void doAuthenticate(String email, String password) {

        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(email, password);
        try {
            manager.authenticate(authentication);


        } catch (BadCredentialsException e) {
            throw new BadCredentialsException(" Invalid Username or Password  !!");
        }

    }
    //this should be added in exceptions class
    @ExceptionHandler(BadCredentialsException.class)
    public String exceptionHandler() {
        return "Invalid Credentials !!";
    }

    @GetMapping("/validateToken")
    public boolean validateToken(@RequestHeader("Authorization") String token){

        if (token != null && token.startsWith("Bearer ")) {
            token = token.substring(7);

        }

        return authService.validateToken(token);

    }
}
