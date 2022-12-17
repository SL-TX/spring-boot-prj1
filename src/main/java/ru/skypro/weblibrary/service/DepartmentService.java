package ru.skypro.weblibrary.service;

import ru.skypro.weblibrary.entity.Employee;

import java.util.Collection;
import java.util.List;
import java.util.Map;

public interface DepartmentService {
    Map<Integer, List<Employee>> getEmployees();

    Integer getSalarySum(Integer id);

    Integer getSalaryMin(Integer id);

    Integer getSalaryMax(Integer id);
}
