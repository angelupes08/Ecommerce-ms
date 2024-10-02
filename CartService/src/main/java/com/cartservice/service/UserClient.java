package com.cartservice.service;

import com.cartservice.model.Users;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(name = "USER-SERVICE")
public interface UserClient {

    @GetMapping("/user/userInfo")
    public Users getLoggedInUserInfo(@RequestHeader("Authorization") String token);

    @GetMapping("/user/validateToken")
    public boolean validateToken(@RequestHeader("Authorization") String token);
}
