package ru.skypro.weblibrary.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.skypro.weblibrary.entity.Employee;
import ru.skypro.weblibrary.service.DepartmentService;

import java.util.Collection;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("department")
public class DepartmentController {
    private final DepartmentService departmentService;

    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @GetMapping("/employees")
    public Map<Integer, List<Employee>> getEmployees(){
        return departmentService.getEmployees();
    }

    @GetMapping("/{id}/salary/sum")
    public Integer getSalarySum(@PathVariable Integer id){
        return departmentService.getSalarySum(id);
    }
    @GetMapping("/{id}/salary/min")
    public Integer getSalaryMin(@PathVariable Integer id){
        return departmentService.getSalaryMin(id);
    }
    @GetMapping("/{id}/salary/max")
    public Integer getSalaryMax(@PathVariable Integer id){
        return departmentService.getSalaryMax(id);
    }
}
