package io.grocery.backend.service;


import java.util.List;

import io.grocery.backend.dto.EmployeeDto;
import io.grocery.backend.entity.Employee;
import io.grocery.backend.repository.EmployeeRepository;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;


@Service
public class EmployeeService {
    private final EmployeeRepository employeeRepository;

    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public Employee createEmployee(EmployeeDto employeeDTO) {
        Employee employee = new Employee();
        BeanUtils.copyProperties(employeeDTO, employee);
        return employeeRepository.save(employee);
    }

    public Employee updateEmployee(Long id, EmployeeDto employeeDTO) {
        Employee existingEmployee = getEmployeeById(id);
        BeanUtils.copyProperties(employeeDTO, existingEmployee);
        return employeeRepository.save(existingEmployee);
    }

    public void deleteEmployee(Long id) {
        employeeRepository.deleteById(id);
    }

    public Employee getEmployeeById(Long id) {
        return employeeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Employee not found"));
    }

    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }
}
