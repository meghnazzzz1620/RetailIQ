package com.retailiq.service;

import com.retailiq.dto.EmployeeRequest;
import com.retailiq.dto.EmployeeResponse;
import com.retailiq.entity.Employee;
import com.retailiq.exception.BusinessException;
import com.retailiq.mapper.EmployeeMapper;
import com.retailiq.repository.EmployeeRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {

    private final EmployeeRepository employeeRepository;

    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public EmployeeResponse createEmployee(EmployeeRequest request) {

        if (employeeRepository.existsByEmail(request.getEmail())) {
            throw new BusinessException("Email already exists.");
        }

        if (employeeRepository.existsByPhone(request.getPhone())) {
            throw new BusinessException("Phone number already exists.");
        }

        Employee employee = EmployeeMapper.toEntity(request);

        Employee savedEmployee = employeeRepository.save(employee);

        return EmployeeMapper.toResponse(savedEmployee);
    }

    public List<EmployeeResponse> getAllEmployees() {

        return employeeRepository.findByStatusTrue()
                .stream()
                .map(EmployeeMapper::toResponse)
                .toList();
    }

    public EmployeeResponse getEmployeeById(Long id) {

        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() ->
                        new BusinessException("Employee not found."));

        return EmployeeMapper.toResponse(employee);

    }

    public EmployeeResponse updateEmployee(
            Long id,
            EmployeeRequest request) {

        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() ->
                        new BusinessException("Employee not found."));

        employee.setFirstName(request.getFirstName());
        employee.setLastName(request.getLastName());
        employee.setEmail(request.getEmail());
        employee.setPhone(request.getPhone());
        employee.setDesignation(request.getDesignation());
        employee.setDepartment(request.getDepartment());
        employee.setSalary(request.getSalary());

        employee.setUpdatedAt(java.time.LocalDateTime.now());

        Employee updated = employeeRepository.save(employee);

        return EmployeeMapper.toResponse(updated);

    }

    public void deleteEmployee(Long id) {

        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() ->
                        new BusinessException("Employee not found."));

        employee.setStatus(false);

        employee.setUpdatedAt(java.time.LocalDateTime.now());

        employeeRepository.save(employee);

    }

}