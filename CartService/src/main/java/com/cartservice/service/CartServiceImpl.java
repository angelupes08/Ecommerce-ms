package com.cartservice.service;

import com.cartservice.model.*;
import com.cartservice.payload.CartDto;
import com.cartservice.repository.CartItemsRepository;
import com.cartservice.repository.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CartServiceImpl implements CartService {

    @Autowired
    CartRepository cartRepository;

    @Autowired
    CartItemsRepository cartItemsRepository;

    @Autowired
    ProductClient productClient;

    @Override
    public Cart findActiveCart(Users users) {

        Optional<Cart> cart = cartRepository.findByUserIdAndCartStatus(users.getId(), CartStatus.ACTIVE);

        if (!cart.isPresent()) {
            return this.createActiveCart(users);
        }

        return cart.get();
    }

    @Override
    public Cart createActiveCart(Users users) {

        Cart cart = new Cart();
        cart.setUserId(users.getId());
        cart.setCartStatus(CartStatus.ACTIVE);

        return cartRepository.save(cart);
    }

    @Override
    public CartItems addProductstoCart(Users user, Long productId, int quantity) {

        Cart cart = findActiveCart(user);

        Products products = productClient.getProductsById(productId);

        CartItems cartItems = new CartItems();

        cartItems.setCart(cart);
        cartItems.setProductId(products.getId());
        cartItems.setQuantity(quantity);
        cartItems.setPrice(products.getPrice() * quantity);

        cartItems = cartItemsRepository.save(cartItems);

        return cartItems;
    }

    @Override
    public List<CartDto> cartReview(Users user) {

        Cart cart = findActiveCart(user);

        List<CartItems> cartItemsList = cartItemsRepository.findByCart(cart);

        List<CartDto> cartDtos = new ArrayList<>();

        //return cartItemsList.stream().map((items)->this.modelMapper.map(items,CartDto.class)).collect(Collectors.toList());

        for (CartItems c : cartItemsList) {

            CartDto cartDto = new CartDto();

            Products productDto = productClient.getProductsById(c.getProductId());

            cartDto.setProductDto(productDto);
            cartDto.setQuantity(c.getQuantity());
            cartDto.setPrice(c.getPrice());

            cartDtos.add(cartDto);

        }
        return cartDtos;
    }

    @Override
    public void markCartAsInactive(Cart cart) {

        cart.setCartStatus(CartStatus.INACTIVE);
        cartRepository.save(cart);
    }

    @Override
    public List<CartItems> getCartItemsFromCart(Cart cart) {
        return cartItemsRepository.findByCart(cart);
    }

    @Override
    public double findCartPrice(Cart cart) {
        return cartItemsRepository.findTotalPriceByCartId(cart);
    }

    @Override
    public void reduceItemsQuantity(Users user, Long productId) {

    }

}
