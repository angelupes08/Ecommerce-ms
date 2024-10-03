package com.userservice.service;

import com.userservice.model.Users;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "CART-SERVICE")
public interface CartClient {

    @PostMapping("/cart/new")
    public void createActiveCart(@RequestBody Users user);
}
