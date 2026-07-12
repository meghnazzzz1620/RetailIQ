package com.retailiq.repository;

import com.retailiq.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CustomerRepository extends JpaRepository<Customer,Long>{

    boolean existsByEmail(String email);

    boolean existsByPhone(String phone);

    List<Customer> findByStatusTrue();
    long countByStatusTrue();

}