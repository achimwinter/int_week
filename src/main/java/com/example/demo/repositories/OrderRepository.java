package com.example.demo.repositories;

import com.example.demo.models.Order;
import com.example.demo.models.OrderList;
import com.example.demo.models.Product;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    Order findOrderByProductAndOrderList(Product product, OrderList orderList);
}