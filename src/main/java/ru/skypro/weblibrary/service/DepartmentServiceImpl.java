package ru.skypro.weblibrary.service;

import org.springframework.stereotype.Service;
import ru.skypro.weblibrary.entity.Employee;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class DepartmentServiceImpl implements DepartmentService {

    final EmployeeService employeeService;

    public DepartmentServiceImpl(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @Override
    public Map<Integer, List<Employee>> getEmployees() {
        return employeeService.getListEmployee().stream().collect(Collectors.groupingBy(Employee::getDepartment));
    }

    @Override
    public Integer getSalarySum(Integer id) {
        return employeeService.getListEmployee().stream()
                .filter(e-> e.getDepartment().equals(id))
                .mapToInt(Employee::getSalary)
                .sum();
    }

    @Override
    public Integer getSalaryMin(Integer id) {
        return employeeService.getListEmployee().stream()
                .filter(e-> e.getDepartment().equals(id))
                .mapToInt(Employee::getSalary)
                .min().orElseThrow();
    }

    @Override
    public Integer getSalaryMax(Integer id) {
        return employeeService.getListEmployee().stream()
                .filter(e-> e.getDepartment().equals(id))
                .mapToInt(Employee::getSalary)
                .max().orElseThrow();
    }
}
