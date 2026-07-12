package com.retailiq.specification;

import com.retailiq.entity.Product;
import org.springframework.data.jpa.domain.Specification;

public class ProductSpecification {

    public static Specification<Product> hasKeyword(String keyword) {

        return (root, query, cb) -> {

            if (keyword == null || keyword.isBlank()) {
                return cb.conjunction();
            }

            String pattern = "%" + keyword.toLowerCase() + "%";

            return cb.or(

                    cb.like(
                            cb.lower(root.get("productName")),
                            pattern
                    ),

                    cb.like(
                            cb.lower(root.get("sku")),
                            pattern
                    )

            );

        };

    }

}