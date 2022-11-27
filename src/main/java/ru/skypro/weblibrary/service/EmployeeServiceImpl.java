package ru.skypro.weblibrary.service;

import org.springframework.stereotype.Service;
import ru.skypro.weblibrary.dto.EmployeeRequest;
import ru.skypro.weblibrary.entity.Employee;

import java.util.*;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private final Map<Integer,Employee> employees = new HashMap<>();

    @Override
    public Collection<Employee> getListEmployee() {
        return this.employees.values();
    }

    @Override
    public Employee addEmployee(EmployeeRequest employeeRequest) {
        if (employeeRequest.getFirstName() == null || employeeRequest.getLastName() == null) {
            throw new IllegalArgumentException("Employee name should be set");
        }
        Employee employee = new Employee(employeeRequest.getFirstName(), employeeRequest.getLastName(), employeeRequest.getSecondName(),
                employeeRequest.getDepartment(), employeeRequest.getSalary());
        this.employees.put(employee.getId(),employee);
        return employee;
    }

    @Override
    public Integer getSumAlary() {
        return employees.values().stream().mapToInt(Employee::getSalary).sum();
    }

    @Override
    public Employee getMinAlary() {
        return employees.values().stream().reduce((e1, e2) -> e1.getSalary() < e2.getSalary() ? e1 : e2).orElseThrow();
    }

    @Override
    public Employee getMaxAlary() {
        return employees.values().stream().reduce((e1, e2) -> e1.getSalary() > e2.getSalary() ? e1 : e2).orElseThrow();
    }

    @Override
    public List<Employee> getHighSalary(Integer salary) {
        return employees.values().stream().filter(e -> e.getSalary() >= salary).toList();
    }
}
