package com.test.server.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Objects;
import java.util.Set;

/**
 * Employee entity
 */
@Document(collation = "Employee")
@AllArgsConstructor
@NoArgsConstructor
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
    private String  Position;
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
    @Getter @Setter private Set<Task> tasks;

    /**
     * add tasks to Employee
     * @param newTask task object
     */
    public void addTask(Task newTask) {
        tasks.add(newTask);
    }

    /**
     * delete task from Employee
     * @param task
     */
    public void deleteTask(Task task) {
        tasks.remove(task);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Employee employee = (Employee) o;
        return Objects.equals(id, employee.id) && Objects.equals(firstName, employee.firstName) && Objects.equals(lastName, employee.lastName) && Objects.equals(Position, employee.Position) && Objects.equals(address, employee.address) && Objects.equals(phone, employee.phone) && Objects.equals(tasks, employee.tasks);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstName, lastName, Position, address, phone, tasks);
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id='" + id + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", Position='" + Position + '\'' +
                ", address='" + address + '\'' +
                ", phone='" + phone + '\'' +
                ", tasks=" + tasks +
                '}';
    }
}
