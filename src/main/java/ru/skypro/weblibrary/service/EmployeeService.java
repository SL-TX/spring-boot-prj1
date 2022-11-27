package ru.skypro.weblibrary.service;

import ru.skypro.weblibrary.dto.EmployeeRequest;
import ru.skypro.weblibrary.entity.Employee;

import java.util.Collection;
import java.util.List;

public interface EmployeeService {

    Collection<Employee> getListEmployee();

    Employee addEmployee(EmployeeRequest employeeRequest);

    Integer getSumAlary();

    Employee getMinAlary();

    Employee getMaxAlary();

    List<Employee> getHighSalary(Integer salary);
}
