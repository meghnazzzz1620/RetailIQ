package com.retailiq.repository;

import com.retailiq.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    boolean existsByEmail(String email);

    Optional<Employee> findByEmail(String email);
    boolean existsByPhone(String phone);

    List<Employee> findByStatusTrue();

}