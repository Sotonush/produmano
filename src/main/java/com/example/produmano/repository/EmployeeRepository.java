package com.example.produmano.repository;

import com.example.produmano.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    List<Employee> findByStatus(String status);

    Optional<Employee> findByEmail(String email);
}
