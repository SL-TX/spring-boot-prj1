package ru.skypro.weblibrary.service;

import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import ru.skypro.weblibrary.dto.EmployeeRequest;
import ru.skypro.weblibrary.entity.Employee;


import java.util.HashMap;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class EmployeeServiceTest {

    @NotNull
    private static Stream<Arguments> provideParamsForTest(){
        return Stream.of(
                Arguments.of("FirstNameA","LastNameA","SecondNameA",1,1000),
                Arguments.of("FirstNameB","LastNameB","SecondNameB",1,6000),
                Arguments.of("FirstNameC","LastNameC","SecondNameC",1,2000),
                Arguments.of("FirstNameD","LastNameD","SecondNameD",2,5000),
                Arguments.of("FirstNameE","LastNameE","SecondNameE",2,1000),
                Arguments.of("FirstNameF","LastNameF","SecondNameF",2,3000),
                Arguments.of("FirstNameG","LastNameG","SecondNameG",1,5000)
        );
    }
    private final EmployeeService employeeService = new EmployeeServiceImpl();

    @Test
    void getListEmployee() {
        var expected = new HashMap<Integer, Employee>().values();
        var actual = employeeService.getListEmployee();
        assertIterableEquals(expected,actual);
    }

    @ParameterizedTest
    @MethodSource("provideParamsForTest")
    void addEmployee(String paramFirstName,String paramLastName,String paramSecondName,Integer paramDepartment,Integer paramSalary) {
        EmployeeRequest employee = new EmployeeRequest();
        employee.setFirstName(paramFirstName);
        employee.setLastName(paramLastName);
        employee.setSecondName(paramSecondName);
        employee.setDepartment(paramDepartment);
        employee.setSalary(paramSalary);
        employeeService.addEmployee(employee);
        Employee actual = (Employee) employeeService.getListEmployee().toArray()[0];
        assertEquals(paramFirstName,actual.getFirstName());
        assertEquals(paramLastName,actual.getLastName());
        assertEquals(paramSecondName,actual.getSecondName());
        assertEquals(paramDepartment,actual.getDepartment());
        assertEquals(paramSalary,actual.getSalary());
    }

    @ParameterizedTest
    @MethodSource("provideParamsForTest")
    void getSumSalary(String paramFirstName, String paramLastName, String paramSecondName, Integer paramDepartment, Integer paramSalary) {
        addEmployee(paramFirstName,paramLastName,paramSecondName,paramDepartment,paramSalary);
        addEmployee(paramFirstName,paramLastName,paramSecondName,paramDepartment,paramSalary);
        assertEquals(paramSalary*2,employeeService.getSumSalary());
    }

    @Test
    void getMinSalary() {
    }

    @Test
    void getMaxSalary() {
    }

    @Test
    void getHighSalary() {
    }
}