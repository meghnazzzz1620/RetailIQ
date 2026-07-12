package com.retailiq.service;

import com.retailiq.dto.CustomerRequest;
import com.retailiq.dto.CustomerResponse;
import com.retailiq.entity.Customer;
import com.retailiq.exception.BusinessException;
import com.retailiq.mapper.CustomerMapper;
import com.retailiq.repository.CustomerRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService {

    private final CustomerRepository customerRepository;

    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public CustomerResponse createCustomer(CustomerRequest request) {

        if (customerRepository.existsByEmail(request.getEmail())) {
            throw new BusinessException("Email already exists.");
        }

        if (customerRepository.existsByPhone(request.getPhone())) {
            throw new BusinessException("Phone number already exists.");
        }

        Customer customer = CustomerMapper.toEntity(request);

        Customer savedCustomer = customerRepository.save(customer);

        return CustomerMapper.toResponse(savedCustomer);
    }

    public List<CustomerResponse> getAllCustomers() {

        return customerRepository.findByStatusTrue()
                .stream()
                .map(CustomerMapper::toResponse)
                .toList();
    }
    public CustomerResponse getCustomerById(Long id) {

        Customer customer = customerRepository.findById(id)
                .orElseThrow(() ->
                        new BusinessException("Customer not found."));

        return CustomerMapper.toResponse(customer);

    }

    public CustomerResponse updateCustomer(
            Long id,
            CustomerRequest request) {

        Customer customer = customerRepository.findById(id)
                .orElseThrow(() ->
                        new BusinessException("Customer not found."));

        customer.setFirstName(request.getFirstName());
        customer.setLastName(request.getLastName());
        customer.setEmail(request.getEmail());
        customer.setPhone(request.getPhone());
        customer.setAddress(request.getAddress());
        customer.setCity(request.getCity());
        customer.setState(request.getState());
        customer.setCountry(request.getCountry());
        customer.setPostalCode(request.getPostalCode());

        customer.setUpdatedAt(java.time.LocalDateTime.now());

        Customer updated = customerRepository.save(customer);

        return CustomerMapper.toResponse(updated);

    }

    public void deleteCustomer(Long id) {

        Customer customer = customerRepository.findById(id)
                .orElseThrow(() ->
                        new BusinessException("Customer not found."));

        customer.setStatus(false);

        customer.setUpdatedAt(java.time.LocalDateTime.now());

        customerRepository.save(customer);

    }

}