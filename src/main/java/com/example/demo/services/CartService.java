package com.example.demo.services;

import com.example.demo.models.OrderList;
import com.example.demo.models.User;
import com.example.demo.repositories.OrderListRepository;
import com.example.demo.repositories.OrderRepository;
import com.example.demo.repositories.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private OrderListRepository orderListRepository;

    public OrderList getActiveOrderList(User user) {
        return orderListRepository.getOrderListByUserAndCheckoutIs(user, false).get(0);
    }

    public List<OrderList> getCompletedOrders(User user) {
        return orderListRepository.getOrderListByUserAndCheckoutIs(user, true);
    }

}
