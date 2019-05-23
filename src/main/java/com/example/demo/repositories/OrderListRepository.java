package com.example.demo.repositories;

import com.example.demo.models.OrderList;
import com.example.demo.models.User;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderListRepository extends JpaRepository<OrderList, Long> {
    List<OrderList> getOrderListByUserAndCheckoutIs(User user, Boolean checkout);
}