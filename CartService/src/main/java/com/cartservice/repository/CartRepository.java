package com.cartservice.repository;

import com.cartservice.model.Cart;
import com.cartservice.model.CartStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CartRepository extends JpaRepository<Cart,Long> {

    public Optional<Cart> findByUserIdAndCartStatus(Long userId, CartStatus cartStatus);
}
