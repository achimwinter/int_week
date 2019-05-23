package com.example.demo.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    // Wird aktuell nicht benutzt evtl entfernen oder Dinge von WelcomeController hier her verschieben

    //    @Autowired
    //    private ReviewService reviewService;
    //
    //    @RequestMapping(method = RequestMethod.GET)
    //    public List<Product> getAllProducts(@RequestParam @Nullable String category) {
    //        // TODO Pagination
    //        System.out.println("test");
    //        return productRepository.findAll();
    //    }
    //
    //    @RequestMapping(method = RequestMethod.POST)
    //    public Product createProduct(@RequestBody Product product)  {
    //        return productRepository.save(product);
    //    }

}
