package com.cartservice.service;

import com.cartservice.model.Cart;
import com.cartservice.model.CartItems;
import com.cartservice.model.Users;
import com.cartservice.payload.CartDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CartService {

    public Cart findActiveCart(Users users);

    public Cart createActiveCart(Users users);

    public CartItems addProductstoCart(Users user, Long productId, int quantity);

    public List<CartDto> cartReview(Users user);

    public void markCartAsInactive(Cart cart);

    public List<CartItems> getCartItemsFromCart(Cart cart);

    public double findCartPrice(Cart cart);

    public void reduceItemsQuantity(Users user,Long productId);


}
