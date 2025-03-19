package com.example.produmano.repository;

import com.example.produmano.entity.Employee;
import com.example.produmano.enums.EmployeeStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    List<Employee> findByStatus(String status);

    Optional<Employee> findByEmail(String email);
    Optional<Employee> findByPhone(String phone);
    List<Employee> findByStatus(EmployeeStatus status);
}
