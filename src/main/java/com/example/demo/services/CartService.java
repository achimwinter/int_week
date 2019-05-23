package com.example.demo.services;

import lombok.val;

import com.example.demo.models.Order;
import com.example.demo.models.OrderList;
import com.example.demo.models.Product;
import com.example.demo.models.User;
import com.example.demo.repositories.OrderListRepository;
import com.example.demo.repositories.OrderRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;

@Service
public class CartService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private OrderListRepository orderListRepository;


    public List<OrderList> getCompletedOrders(User user) {
        return orderListRepository.getOrderListByUserAndCheckoutIs(user, true);
    }

    public OrderList getOrCreateOrderList(User user) {
        val lst = orderListRepository.getOrderListByUserAndCheckoutIs(user, false);
        if (lst.isEmpty()) {
            return orderListRepository.save(OrderList.builder()
                .user(user)
                .orders(new HashSet<>())
                .checkout(false)
                .build());
        }
        return lst.get(0);
    }

    public OrderList checkout(OrderList lst) {
        lst.setCheckout(true);
        orderListRepository.save(lst);
        return lst;
    }

    public OrderList saveOrderList(OrderList lst) {
        orderListRepository.save(lst);
        return lst;
    }


    public Order saveOrder(Order lst) {
        orderRepository.save(lst);
        return lst;
    }

    public Order changeOrder(OrderList orderList, Product product, Long amount){
        Order order = orderRepository.findOrderByProductAndOrderList(product, orderList);
        if (order != null){
            order.setAmount(order.getAmount() + amount);
        }else {
            order = new Order();
            order.setAmount(amount);
            order.setOrderList(orderList);
            order.setProduct(product);
        }

        order = saveOrder(order);
        if(order.getAmount() == 0){
            deleteOrder(orderList, product);
            return null;
        }else {
            return order;
        }

    }

    public boolean deleteOrder(OrderList orderList, Product product){
        Order order = orderRepository.findOrderByProductAndOrderList(product, orderList);
        if (order != null){
            orderRepository.delete(order);
            return true;
        }
        return false;
    }

}
