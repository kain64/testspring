package com.test.server.repositories;

import com.test.server.entity.Employee;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * Employee mongo Repository
 */
public interface EmployeeRepository extends MongoRepository<Employee, String> {
}

