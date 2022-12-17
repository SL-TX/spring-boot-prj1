package ru.skypro.weblibrary.service;

import org.apache.commons.lang3.StringUtils;
import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.web.server.ResponseStatusException;
import ru.skypro.weblibrary.dto.EmployeeRequest;
import ru.skypro.weblibrary.entity.Employee;


import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class EmployeeServiceTest {

    @NotNull
    private static Stream<Arguments> provideParamsForTest(){
        return Stream.of(
                Arguments.of("FirstNameA","LastNameA","SecondNameA",1,1000),
                Arguments.of("FirstNameB","LastNameB","SecondNameB",1,6000),
                Arguments.of("FirstNameC","LastNameC","SecondNameC",1,null),
                Arguments.of("FirstNameD",null,"SecondNameD",2,5000),
                Arguments.of(null,"LastNameE","SecondNameE",2,1000),
                Arguments.of("FirstNameF","LastNameF",null,2,3000),
                Arguments.of("FirstNameG","LastNameG","SecondNameG",null,5000)
        );
    }
    private final EmployeeService employeeService = new EmployeeServiceImpl();

    private final Map<Integer,Employee> testDb= new HashMap<>();

    void initSomeEntites(){
        EmployeeRequest employee;
        var letArr = new String[]{"A", "B", "C", "D","E"};
        var i = 0;
        for (String let:letArr
             ) {
            i++;
            employee = new EmployeeRequest();
            employee.setFirstName("FirstName"+let);
            employee.setLastName("LastName"+let);
            employee.setSecondName("SecondName"+let);
            employee.setDepartment(i%2+1);
            employee.setSalary((i%3+1)*1000);
            employeeService.addEmployee(employee);
        }
    }

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
        if (!StringUtils.isAlpha(paramFirstName)
        && !StringUtils.isAlpha(paramSecondName)
                && !StringUtils.isAlpha(paramLastName) || paramSecondName == null){
            assertThrows(ResponseStatusException.class,()->employeeService.addEmployee(employee));
        } else
        if (paramFirstName == null || paramLastName == null) {
            assertThrows(IllegalArgumentException.class,()->employeeService.addEmployee(employee));
        } else
        if (paramSalary == null || paramDepartment == null){
            assertThrows(IllegalArgumentException.class,()->employeeService.addEmployee(employee));
        } else {
            employeeService.addEmployee(employee);
            Employee actual = (Employee) employeeService.getListEmployee().toArray()[0];
            assertEquals(paramFirstName, actual.getFirstName());
            assertEquals(paramLastName, actual.getLastName());
            assertEquals(paramSecondName, actual.getSecondName());
            assertEquals(paramDepartment, actual.getDepartment());
            assertEquals(paramSalary, actual.getSalary());
        }
    }

    @Test
    void getSumSalary() {
        initSomeEntites();
        assertEquals(2*2000+2*3000+1000,employeeService.getSumSalary());
    }

    @Test
    void getMinSalary() {
        initSomeEntites();
        assertEquals(employeeService.getListEmployee().toArray()[2],employeeService.getMinSalary());
    }

    @Test
    void getMaxSalary() {
        initSomeEntites();
        assertEquals(employeeService.getListEmployee().toArray()[4],employeeService.getMaxSalary());
    }

    @Test
    void getHighSalary() {
        initSomeEntites();
        assertEquals(employeeService.getListEmployee().stream()
                .filter(e->e.getSalary()>=2001).collect(Collectors.toList()),
                employeeService.getHighSalary(2001));
    }
}