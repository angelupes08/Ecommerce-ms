package com.cartservice.repository;

import com.cartservice.model.Cart;
import com.cartservice.model.CartItems;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CartItemsRepository extends JpaRepository<CartItems,Long> {

    public List<CartItems> findByCart(Cart cart);

    @Query("SELECT SUM(c.price*c.quantity) from CartItems c where c.cart=:cart group by c.cart")
    public double findTotalPriceByCartId(Cart cart);

    public int findQuantityById(Long id);
}
