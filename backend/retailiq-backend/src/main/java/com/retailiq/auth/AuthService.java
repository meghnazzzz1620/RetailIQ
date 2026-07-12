package com.retailiq.auth;

import com.retailiq.entity.Employee;
import com.retailiq.exception.BusinessException;
import com.retailiq.jwt.JwtService;
import com.retailiq.repository.EmployeeRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final EmployeeRepository employeeRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    public AuthService(
            EmployeeRepository employeeRepository,
            PasswordEncoder passwordEncoder,
            JwtService jwtService) {

        this.employeeRepository = employeeRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
    }

    public JwtResponse login(LoginRequest request) {

        Employee employee = employeeRepository.findByEmail(request.getEmail())

                .orElseThrow(() ->
                        new BusinessException("Invalid email or password"));

        if (!passwordEncoder.matches(
                request.getPassword(),
                employee.getPassword())) {

            throw new BusinessException("Invalid email or password");
        }

        String token =
                jwtService.generateToken(employee.getEmail());

        return new JwtResponse(

                token,

                employee.getEmail(),

                employee.getRole().name()

        );
    }
    public JwtResponse register(RegisterRequest request) {

        if (employeeRepository.findByEmail(request.getEmail()).isPresent()) {

            throw new BusinessException("Email already exists.");

        }

        Employee employee = Employee.builder()

                .firstName(request.getFirstName())

                .lastName(request.getLastName())

                .email(request.getEmail())

                .password(
                        passwordEncoder.encode(
                                request.getPassword()))

                .role(request.getRole())

                .status(true)

                .designation("Employee")

                .department("General")

                .salary(30000.0)

                .createdAt(java.time.LocalDateTime.now())

                .updatedAt(java.time.LocalDateTime.now())

                .build();

        employeeRepository.save(employee);

        String token =
                jwtService.generateToken(employee.getEmail());

        return new JwtResponse(

                token,

                employee.getEmail(),

                employee.getRole().name()

        );

    }

}