package com.test.server;

import com.test.server.repositories.EmployeeRepository;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TestService {

    @Autowired
    @Getter
    private EmployeeRepository employeeRepository;
}
