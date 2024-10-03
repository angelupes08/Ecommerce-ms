package com.cartservice.controller;

import com.cartservice.exception.UnauthorizedException;
import com.cartservice.model.Cart;
import com.cartservice.model.CartItems;
import com.cartservice.model.Users;
import com.cartservice.payload.CartDto;
import com.cartservice.service.CartService;
import com.cartservice.service.UserClient;
import feign.FeignException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cart")
public class CartController {

    private CartService cartService;

    private UserClient userClient;

    private String token;

    public CartController(CartService cartService,UserClient userClient){

        this.cartService=cartService;
        this.userClient=userClient;
    }
    //@Operation(summary = "Add products to the cart")
    @PostMapping("/product/{productId}")
    public ResponseEntity<String> addProducttoCart(@RequestHeader("Authorization")String authHeader,
                                                    @PathVariable("productId") Long productId,
                                                   @RequestParam(value = "quantity")int quantity) throws UnauthorizedException {

        this.validateToken(authHeader);

        Users user = userClient.getLoggedInUserInfo(authHeader.substring(7));

        CartItems cartItems = cartService.addProductstoCart(user,productId,quantity);

        return new ResponseEntity<>("Product added to cart", HttpStatus.CREATED);
    }

    //Review cart
    //@Operation(summary = "Review items in the cart")
    @GetMapping("")
    public ResponseEntity<List<CartDto>> reviewCart(@RequestHeader("Authorization")String authHeader) throws UnauthorizedException {

        this.validateToken(authHeader);

        Users user = userClient.getLoggedInUserInfo(authHeader.substring(7));

        return new ResponseEntity<>(cartService.cartReview(user),HttpStatus.OK);
    }

    //@Operation(summary = "Find total price of items in the cart")
    @GetMapping("/price")
    public ResponseEntity<Double> findCartPrice(@RequestHeader("Authorization")String authHeader) throws UnauthorizedException {

        this.validateToken(authHeader);

        Users user = userClient.getLoggedInUserInfo(authHeader.substring(7));

        Cart cart = cartService.findActiveCart(user);

        return new ResponseEntity<>(cartService.findCartPrice(cart),HttpStatus.OK);
    }

    @PostMapping("/new")
    public void createActiveCart(@RequestBody Users user){

        cartService.createActiveCart(user);
    }

    public void validateToken(String authHeader) throws UnauthorizedException {

        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            token = authHeader.substring(7);

        }
        else{
            throw new UnauthorizedException("Access Denied");
        }

        try{
            Boolean isValid = userClient.validateToken(token);

            if(!isValid){

                throw new UnauthorizedException("Access Denied");

            }
        }

        catch (FeignException e) {

            throw new UnauthorizedException("Token validation failed");
        }
    }
}
