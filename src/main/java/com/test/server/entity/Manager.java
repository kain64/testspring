package com.test.server.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

/**
 * Manager entity
 */
@Document(collection = "Manager")
@AllArgsConstructor
@NoArgsConstructor
public class Manager extends Employee {
    @Id
    @Setter
    @Getter
    public String id;

    /**
     * List of related Employees
     */
    @DBRef
    @Getter @Setter
    private List<Employee> Employees;

    /**
     * List of related Employees
     */
    @Getter @Setter
    private List<Report> reports;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Manager manager = (Manager) o;
        return Objects.equals(id, manager.id) && Objects.equals(Employees, manager.Employees);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), id, Employees);
    }
}
