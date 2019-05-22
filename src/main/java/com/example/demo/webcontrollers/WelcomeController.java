package com.example.demo.webcontrollers;

import com.example.demo.models.OrderList;
import com.example.demo.models.Product;
import com.example.demo.repositories.ProductRepository;
import com.example.demo.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Arrays;
import java.util.List;

@Controller
public class WelcomeController {

//    @Autowired
//    ProductRepository productRepository;

//    private List<String> tasks = Arrays.asList("a", "b", "c", "d", "e", "f", "g");

    @GetMapping("/")
    public String main(Model model) {
//        model.addAttribute("tasks", tasks);
//        model.addAttribute("products", articleRepository.findAll());

        ProductService productService = new ProductService();
        List<Product> allProducts = productService.list();

        model.addAttribute("prods", allProducts);

        return "shop"; //view
    }


    @GetMapping("/cart")
    public String getCart(Model model) {
        //todo: get cart either by session OR parameter (bsp /hello) OR path variable (bsp /article/id) and add it to model
        OrderList testcart = new OrderList();
        model.addAttribute("cart", testcart);
        return "cart";
    }

    @GetMapping("/article/{id}")
    public String getSingleArticle(Model model, @PathVariable String id) {
        // todo: get single article and set into model attribute article
        Product testarticle = new Product();
        model.addAttribute("article", testarticle);
        return "article";
    }


    @GetMapping("/hello")
    public String mainWithParam(
            @RequestParam(name = "name", required = false, defaultValue = "")
                    String name, Model model) {

        model.addAttribute("message", name);

        return "shop"; //view
    }

}