package ru.skypro.weblibrary.service;

import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.skypro.weblibrary.dto.EmployeeRequest;
import ru.skypro.weblibrary.entity.Employee;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class DepartmentServiceTest {
    static List<Employee> ret = new ArrayList<Employee>();
    @Mock
    EmployeeService employeeService;
    @InjectMocks
    DepartmentServiceImpl departmentService;

    @BeforeAll
    static void init(){
        Employee employee;
        var letArr = new String[]{"A", "B", "C", "D","E"};
        var i = 0;
        for (String let:letArr
        ) {
            i++;
            employee = new Employee(
                    "FirstName"+let,
                    "LastName"+let,
                    "SecondName"+let,
                    i%2+1,
                    (i%3+1)*1000
            );
            ret.add(employee);
        }
    }

    @Test
    void getEmployees() {
        Mockito.when(employeeService.getListEmployee()).thenReturn(ret);
        var expected = ret.stream().collect(Collectors.groupingBy(Employee::getDepartment));
        var actual = departmentService.getEmployees();
        assertEquals(expected,actual);
    }

    @ParameterizedTest
    @ValueSource(ints = {1, 2})
    void getSalarySum(Integer id) {
        Mockito.when(employeeService.getListEmployee()).thenReturn(ret);
        var expected = ret.stream().filter(e-> e.getDepartment().equals(id))
                .mapToInt(Employee::getSalary)
                .sum();
        var actual = departmentService.getSalarySum(id);
        assertEquals(expected,actual);
    }

    @ParameterizedTest
    @ValueSource(ints = {1, 2})
    void getSalaryMin(Integer id) {
        Mockito.when(employeeService.getListEmployee()).thenReturn(ret);
        var expected = ret.stream().filter(e-> e.getDepartment().equals(id))
                .mapToInt(Employee::getSalary)
                .min().orElseThrow();
        var actual = departmentService.getSalaryMin(id);
        assertEquals(expected,actual);
    }

    @ParameterizedTest
    @ValueSource(ints = {1, 2})
    void getSalaryMax(Integer id) {
        Mockito.when(employeeService.getListEmployee()).thenReturn(ret);
        var expected = ret.stream().filter(e-> e.getDepartment().equals(id))
                .mapToInt(Employee::getSalary)
                .max().orElseThrow();
        var actual = departmentService.getSalaryMax(id);
        assertEquals(expected,actual);
    }
}