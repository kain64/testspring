package com.test.server.repositories;

import com.test.server.entity.Employee;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

/**
 * Employee mongo Repository
 */
public interface EmployeeRepository extends MongoRepository<Employee, String> {
    Optional<Employee> findByLastNameAndFirstName(String lastName,String firstName);

    Optional<Employee> findByEmployeesId(String id);
}

