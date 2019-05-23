package com.example.demo.services;

import lombok.val;
import lombok.var;

import com.example.demo.models.Category;
import com.example.demo.models.Product;
import com.example.demo.repositories.CategoryRepository;
import com.example.demo.repositories.ProductRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    public List<Product> list() {
        return productRepository.findAll();
    }

    public List<Product> getProductsForCategory(Category category) {
        return productRepository.getProductsByCategory(category);
    }

    public Product getByID(Long id) {
        return productRepository.getProductById(id);
    }
    /// create Products by saving them without id
    public Product save(Product product){
        return productRepository.save(product);
    }

    public void delete(Product product){
        productRepository.delete(product);
    }

    public List<Product> search(String s){
        var lstProd = productRepository.findByNameFree(s);
        val lstCat = categoryRepository.findByNameFree(s);
        lstProd.addAll(lstCat.stream().flatMap(x->x.getProducts().stream()).collect(Collectors.toList()));
        lstProd = lstProd.stream().distinct().collect(Collectors.toList());
        return lstProd;
        //return productRepository.findProductsByProductNameRegex(s);
        //return productRepository.findAll(new ProductSpecification(s));
    }



}
