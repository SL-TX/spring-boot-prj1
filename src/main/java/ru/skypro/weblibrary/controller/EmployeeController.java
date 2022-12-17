package ru.skypro.weblibrary.controller;

import org.springframework.web.bind.annotation.*;
import ru.skypro.weblibrary.dto.EmployeeRequest;
import ru.skypro.weblibrary.entity.Employee;
import ru.skypro.weblibrary.service.EmployeeService;

import java.util.Collection;
import java.util.List;

@RestController
public class EmployeeController {

    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping("/employee")
    public Collection<Employee> getListEmployee(){
        return employeeService.getListEmployee();
    }

    @PostMapping("/employee")
    public Employee addEmployee(@RequestBody EmployeeRequest employee){
        return employeeService.addEmployee(employee);
    }

    @GetMapping("employee/salary/sum")
    public Integer getSumAlary(){
        return  employeeService.getSumSalary();
    }
    @GetMapping("employee/salary/min")
    public Employee getMinAlary(){
        return  employeeService.getMinSalary();
    }
    @GetMapping("employee/salary/max")
    public Employee getMaxAlary(){
        return  employeeService.getMaxSalary();
    }
    @GetMapping("employee/high-salary")
    public List<Employee> getHighSalary(@RequestParam Integer salary){
        return  employeeService.getHighSalary(salary);
    }
}
