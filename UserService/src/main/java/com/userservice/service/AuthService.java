package com.userservice.service;

import com.userservice.dtos.AuthRequest;
import com.userservice.dtos.RegisterRequest;
import com.userservice.dtos.UserDto;
import com.userservice.model.Users;
import com.userservice.repository.UserRepository;
import com.userservice.security.JwtHelper;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    JwtHelper jwtHelper;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    ModelMapper modelMapper;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    CartClient cartClient;

    public RegisterRequest register(RegisterRequest request){

        Users user = this.modelMapper.map(request,Users.class);

        user.setPassword(passwordEncoder.encode(user.getPassword()));

        Users savedUser = userRepository.save(user);

        cartClient.createActiveCart(savedUser);

        return this.modelMapper.map(savedUser, RegisterRequest.class);
    }

    public String generateToken(AuthRequest authRequest){

        return jwtHelper.generateToken(new Users(authRequest.getEmail(),authRequest.getPassword()));

    }


    public boolean validateToken(String token){

        return jwtHelper.validateToken(token);
    }

    public UserDto getUserInfo(String token) {

        String email = jwtHelper.getUsernameFromToken(token);

        Users user = userRepository.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException("User not found for the email" + email));

        return this.modelMapper.map(user, UserDto.class);
    }

}
