package ru.skypro.weblibrary.service;

import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
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
        if (!StringUtils.isAlpha(employeeRequest.getFirstName())
                || !StringUtils.isAlpha(employeeRequest.getLastName())
                || !StringUtils.isAlpha(employeeRequest.getSecondName())
        )
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Name shoud be alpha");
        Employee employee = new Employee(
                StringUtils.capitalize(employeeRequest.getFirstName()),
                StringUtils.capitalize(employeeRequest.getLastName()),
                StringUtils.capitalize(employeeRequest.getSecondName()),
                employeeRequest.getDepartment(),
                        employeeRequest.getSalary());
        this.employees.put(employee.getId(),employee);
        return employee;
    }

    @Override
    public Integer getSumSalary() {
        return employees.values().stream().mapToInt(Employee::getSalary).sum();
    }

    @Override
    public Employee getMinSalary() {
        return employees.values().stream().reduce((e1, e2) -> e1.getSalary() < e2.getSalary() ? e1 : e2).orElseThrow();
    }

    @Override
    public Employee getMaxSalary() {
        return employees.values().stream().reduce((e1, e2) -> e1.getSalary() > e2.getSalary() ? e1 : e2).orElseThrow();
    }

    @Override
    public List<Employee> getHighSalary(Integer salary) {
        return employees.values().stream().filter(e -> e.getSalary() >= salary).toList();
    }
}
