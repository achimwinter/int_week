package com.example.demo;

import com.example.demo.models.Category;
import com.example.demo.models.Product;
import com.example.demo.repositories.*;
import lombok.val;
import lombok.var;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.ArrayList;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
//@DataJpaTest
public class DemoApplicationTests {


    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private OrderListRepository orderListRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private ProductRepository productRepository;

    @Test
    public void contextLoads() {
    }

    @Test
    @Transactional
    @Rollback(value = false)
    public void fillDB(){
        Category c1 = categoryRepository.getCategoryByName("books");
        if(c1 == null){
            c1 = categoryRepository.save(Category.builder().name("books").build());
        }

        Category c2 = categoryRepository.getCategoryByName("animals");
        if(c2 == null){
            c2 = categoryRepository.save(Category.builder().name("animals").build());
        }

        val p1 = productRepository.save(Product.builder()
                .category(c1)
                .note("This is a Book")
                .reviews(new ArrayList<>())
                .price(new BigDecimal(42))
                .imagepath("https://upload.wikimedia.org/wikipedia/commons/d/d0/Liji2_no_bg.png")
                .stock(50L)
                .productName("Book")
                .build());
        System.out.println(p1);

        val p2 = productRepository.save(Product.builder()
                .category(c2)
                .note("This is a Horse")
                .reviews(new ArrayList<>())
                .price(new BigDecimal(2599))
                .imagepath("https://upload.wikimedia.org/wikipedia/commons/d/de/Nokota_Horses_cropped.jpg")
                .stock(3L)
                .productName("Horse")
                .build());
        System.out.println(p2);
        System.out.println(productRepository.count());


        categoryRepository.flush();
        productRepository.flush();

    }


    @Test
    @Transactional
    @Rollback(value = false)
    public void loadDB(){
        System.out.println("THIS!!!");
        val lst = productRepository.findAll();
        for(val e : lst){
            System.out.println(e);
        }
        System.out.println(lst.size());



    }

    @Test
    @Transactional
    public void clearDB(){
        productRepository.deleteAll();
        categoryRepository.deleteAll();


    }

}
