package ru.skypro.weblibrary.entity;

public class Employee {
    private static int counter;
    private final Integer id;
    private final String firstName;
    private final String lastName;
    private final String secondName;
    //@Setter
    private final Integer department;
    private final Integer salary;

    public Employee(final String firstName, final String lastName, final String secondName, final Integer department, final Integer salary) {
        this.id = counter++;
        this.firstName = firstName;
        this.lastName = lastName;
        this.secondName = secondName;
        this.department = department;
        this.salary = salary;
    }

    public Integer getId() {
        return id;
    }
    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getSecondName() {
        return secondName;
    }

    public Integer getDepartment() {
        return department;
    }

    public Integer getSalary() {
        return salary;
    }
}
