package com.example.shoesshop.services;

import com.example.shoesshop.entities.CartItem;
import com.example.shoesshop.entities.Order;
import com.example.shoesshop.entities.OrderItem;
import com.example.shoesshop.jpa.OrderItemJPA;
import com.example.shoesshop.jpa.OrderJPA;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class OrderService {
    @Autowired
    private OrderJPA orderJPA;
    @Autowired
    private OrderItemJPA orderItemJPA;

    //hàm lên đơn
    public void makeOrder(String shipAddress,
                          long userId,
                          double total,
                          ArrayList<CartItem> cartItems) {

        Order order = new Order();
        order.setReceivedAddress(shipAddress);
        order.setUserId(userId);
        order.setTotal(total);
        order.setOrderDate("2021-08-02");
        orderJPA.save(order);

        for (int i = 0; i < cartItems.size(); i++) {
            OrderItem orderItem = new OrderItem();
            CartItem cartItem = cartItems.get(i);
            orderItem.setName(cartItem.getName());
            orderItem.setPrice(cartItem.getPrice());
            orderItem.setQuantity(cartItem.getQuantity());
            orderItem.setOrderId(order.getId());
            orderItem.setProductid(cartItem.getId());
            orderItemJPA.save(orderItem);
        }

    }
}
