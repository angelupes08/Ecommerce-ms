package com.categoryservices.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(name="USER-SERVICE")
public interface UserClient {

    @GetMapping("/user/validateToken")
    public boolean validateToken(@RequestHeader("Authorization") String token);
}
