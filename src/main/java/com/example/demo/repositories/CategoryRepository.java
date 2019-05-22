package com.example.demo.repositories;

import com.example.demo.models.Category;
import com.example.demo.models.OrderList;
import com.example.demo.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
    //OrderList getOrderListByUserAndCheckoutIs(User user, Boolean checkout);
    Category getCategoryByName(String name);


}