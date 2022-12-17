package ru.skypro.weblibrary.service;

import ru.skypro.weblibrary.dto.EmployeeRequest;
import ru.skypro.weblibrary.entity.Employee;

import java.util.Collection;
import java.util.List;

public interface EmployeeService {

    Collection<Employee> getListEmployee();

    Employee addEmployee(EmployeeRequest employeeRequest);

    Integer getSumSalary();

    Employee getMinSalary();

    Employee getMaxSalary();

    List<Employee> getHighSalary(Integer salary);
}
