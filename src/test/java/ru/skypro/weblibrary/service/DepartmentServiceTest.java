package ru.skypro.weblibrary.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class DepartmentServiceTest {


    @InjectMocks
    EmployeeService employeeService;
    final DepartmentService departmentService = new DepartmentServiceImpl(employeeService);

    @Test
    void getEmployees() {
    }

    @Test
    void getSalarySum() {
    }

    @Test
    void getSalaryMin() {
    }

    @Test
    void getSalaryMax() {
    }
}