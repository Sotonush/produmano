package com.example.produmano.service;

import com.example.produmano.entity.Employee;
import com.example.produmano.enums.EmployeeStatus;
import com.example.produmano.exseptions.EmployeeNotFoundException;
import com.example.produmano.repository.EmployeeRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {

    private final EmployeeRepository employeeRepository;

    @Autowired
    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public Employee createEmployee(Employee employee) {
        if (employeeRepository.findByEmail(employee.getEmail()).isPresent()) {
            throw new IllegalArgumentException("Сотрудник с таким email уже зарегистрирован.");
        }

        if (employeeRepository.findByPhone(employee.getPhone()).isPresent()) {
            throw new IllegalArgumentException("Сотрудник с таким номером телефона уже зарегистрирован.");
        }

        return employeeRepository.save(employee);
    }


    public Employee getEmployeeById(Long id) {
        return employeeRepository.findById(id)
                .orElseThrow(() -> new EmployeeNotFoundException("Employee not found with id: " + id));
    }

    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    public Employee updateEmployee(Long id, Employee updatedEmployee) {
        Employee existingEmployee = employeeRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Сотрудник с id " + id + " не найден"));

        existingEmployee.setUsername(updatedEmployee.getUsername());
        existingEmployee.setFio(updatedEmployee.getFio());
        existingEmployee.setRoleEmployee(updatedEmployee.getRoleEmployee());
        existingEmployee.setPhone(updatedEmployee.getPhone());
        existingEmployee.setEmail(updatedEmployee.getEmail());
        existingEmployee.setPosition(updatedEmployee.getPosition());
        existingEmployee.setStatus(updatedEmployee.getStatus());
        existingEmployee.setTelegramChatId(updatedEmployee.getTelegramChatId());

        return employeeRepository.save(existingEmployee);
    }


    public void deleteEmployee(Long id) {
        Employee employee = getEmployeeById(id);
        employeeRepository.delete(employee);
    }

    public List<Employee> getEmployeesByStatus(String status) {
        try {
            EmployeeStatus employeeStatus = EmployeeStatus.valueOf(status.toUpperCase());
            return employeeRepository.findByStatus(employeeStatus);
        } catch (IllegalArgumentException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Некорректный статус: " + status);
        }
    }

    public Optional<Employee> getEmployeeByEmail(String email) {
        return employeeRepository.findByEmail(email);
    }

}
