package com.example.demo;

import com.example.demo.models.Category;
import com.example.demo.models.Product;
import com.example.demo.models.Review;
import com.example.demo.models.User;
import com.example.demo.repositories.*;
import com.example.demo.services.ProductService;
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
import java.util.Arrays;
import java.util.Date;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class DemoApplicationTests {

    @Autowired
    private ProductService productService;

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

    @Autowired
    private ReviewRepository reviewRepository;

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


        Category c3 = categoryRepository.getCategoryByName("cars");
        if(c3 == null){
            c3 = categoryRepository.save(Category.builder().name("cars").build());
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

        val p3 = productRepository.save(Product.builder()
                .category(c3)
                .note("a awesome tool for Agriculture")
                .reviews(new ArrayList<>())
                .price(new BigDecimal(348075))
                .imagepath("https://www.fendt.com/de/images/57022e380237fb0f14112f53_1459760702_web_de-DE.jpg")
                .stock(1L)
                .productName("Fendt 1000 Vario")
                .build());
        System.out.println(p3);

        System.out.println(productRepository.count());

        val u1 = userRepository.save(User.builder()
                .email("john.doe@example.com")
                .password("hunter2")
                .username("john_doe")
                .build());

        val u2 = userRepository.save(User.builder()
                .email("achim.winter@gmail.com")
                .password("1906")
                .username("achim_winter")
                .build());

        val u3 = userRepository.save(User.builder()
                .email("anonymous@hack-your-world.com")
                .password("1337")
                .username("anonymous")
                .id(-1L)
                .build());


        val r1 = reviewRepository.save(Review.builder()
                .author(u1)
                .creationDate(new Date())
                .product(p1)
                .content("Awesome Product! \n11/10! would buy again!!!!!111")
                .stars(5L)
                .build());

        val r2 = reviewRepository.save(Review.builder()
                .author(u1)
                .creationDate(new Date())
                .product(p1)
                .content("It's ok i guess.")
                .stars(2L)
                .build());

        val r3 = reviewRepository.save(Review.builder()
                .author(u1)
                .creationDate(new Date())
                .product(p1)
                .content("Now I can start horsing around!")
                .stars(3L)
                .build());


        val r4 = reviewRepository.save(Review.builder()
                .author(u2)
                .creationDate(new Date())
                .product(p3)
                .content("Tottaly awesome!!! \nI already tried it out in Farming Simulator.")
                .stars(5L)
                .build());


        categoryRepository.flush();
        productRepository.flush();
        reviewRepository.flush();

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
    @Rollback(value = false)
    public void clearDB(){
        productRepository.deleteAll();
        categoryRepository.deleteAll();
        reviewRepository.deleteAll();


    }

    @Test
    @Transactional
    public void searchTest(){
        var res = productService.search("horse");
        System.out.println("horse: " + Arrays.toString(res.stream().map(Product::getProductName).toArray()));

        res = productService.search("");
        System.out.println(": " + Arrays.toString(res.stream().map(Product::getProductName).toArray()));

        res = productService.search("Horse");
        System.out.println("Horse: " + Arrays.toString(res.stream().map(Product::getProductName).toArray()));

        res = productService.search("animals");
        System.out.println("animals: " + Arrays.toString(res.stream().map(Product::getProductName).toArray()));
    }

}
