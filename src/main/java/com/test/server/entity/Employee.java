package com.test.server.entity;

import lombok.*;
import lombok.experimental.SuperBuilder;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * Employee entity
 */
@Document(collection = "Employee")
@SuperBuilder
public class Employee {
    @Id
    @Setter
    @Getter
    private String id;

    /**
     * Employee First Name
     */
    @Getter
    @Setter
    private String firstName;
    /**
     * Employee Last Name
     */
    @Getter
    @Setter
    private String  lastName;
    /**
     * Employee Position
     */
    @Getter
    @Setter
    private String position;
    /**
     * Employee address
     */
    @Getter
    @Setter
    private String  address;
    /**
     * Employee phone
     */
    @Getter
    @Setter
    private String  phone;
    /**
     * Assigned tasks
     */
    @Builder.Default
    @Getter @Setter
    private Set<Task>  tasks= new HashSet<>();

    /**
     * photo
     */
    @Getter @Setter private byte[] photo;

    /**
     * default Constructor
     */
    public Employee() {
    }
    /**
     * List of related Employees
     */
    @DBRef
    @Builder.Default
    @Getter @Setter
    private Set<Employee> employees = new HashSet<>();

    /**
     * List of related Employees
     */
    @Getter @Setter
    @Builder.Default
    private Set<Report> reports = new HashSet<>();

    /**
     * add tasks
     * @param newTask task object
     */
    public void addTask(Task newTask) {
        tasks.add(newTask);
    }

    /**
     * delete task from manager
     * @param task task object
     */
    public void deleteTask(Task task) {
        tasks.remove(task);
    }

    /**
     * add report to manager
     * @param newReport task object
     */
    public void addReport(Report newReport) {
        reports.add(newReport);
    }

    /**
     * delete report from manager
     * @param report task object
     */
    public void deleteReport(Report report) {
        reports.remove(report);
    }

    /**
     * add employee to manager
     * @param employee employee object
     */
    public void addEmployee(Employee employee) {
        employees.add(employee);
    }

    /**
     * delete employee from manager
     * @param employee employee object
     */
    public void deleteEmployee(Employee employee) {
        reports.remove(employee);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Employee employee = (Employee) o;
        return Objects.equals(id, employee.id) && Objects.equals(firstName, employee.firstName) && Objects.equals(lastName, employee.lastName) && Objects.equals(position, employee.position) && Objects.equals(address, employee.address) && Objects.equals(phone, employee.phone) && Objects.equals(tasks, employee.tasks) && Arrays.equals(photo, employee.photo) && Objects.equals(employees, employee.employees) && Objects.equals(reports, employee.reports);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(id, firstName, lastName, position, address, phone, tasks, employees, reports);
        result = 31 * result + Arrays.hashCode(photo);
        return result;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id='" + id + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", position='" + position + '\'' +
                ", address='" + address + '\'' +
                ", phone='" + phone + '\'' +
                ", tasks=" + tasks +
                ", photo=" + Arrays.toString(photo) +
                ", employees=" + employees +
                ", reports=" + reports +
                '}';
    }
}
