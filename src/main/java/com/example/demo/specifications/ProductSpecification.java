package com.example.demo.specifications;

import com.example.demo.models.Product;
import lombok.AllArgsConstructor;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

@AllArgsConstructor
public class ProductSpecification implements Specification<Product> {

    private String whatToSearchFor;

    @Override
    public Predicate toPredicate(Root<Product> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
        return builder.like(root.get("productName"), "%" + whatToSearchFor + "%");
    }
}