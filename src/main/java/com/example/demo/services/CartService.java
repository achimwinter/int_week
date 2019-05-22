package com.example.demo.services;

import com.example.demo.models.Order;
import com.example.demo.models.User;
import com.example.demo.repositories.OrderListRepository;
import com.example.demo.repositories.OrderRepository;
import com.example.demo.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

@Service
public class CartService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private OrderListRepository orderListRepository;

    public Set<Order> getActiveOrders(User user) {
        Set<Order> allUserOders = orderListRepository.getOrderListByUser(user).getOrders();
        allUserOders = allUserOders.stream()
                .filter(order -> order.getCheckout().equals(false))
                .collect(Collectors.toSet());

        return allUserOders;
    }
}
