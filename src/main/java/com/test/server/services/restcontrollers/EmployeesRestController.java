package com.test.server.services.restcontrollers;

import com.test.server.entity.Employee;
import com.test.server.entity.Task;
import com.test.server.repositories.EmployeeRepository;
import com.test.server.services.ws.NotificationWebSocketService;
import io.swagger.v3.oas.annotations.Operation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import java.util.List;


/**
 * Rest controller for Employees
 */
@RestController

public class EmployeesRestController {

    /**
     * notification service
     */
    @Autowired
    private NotificationWebSocketService notificationWebSocketService;
    /**
     * Employee mongo repository
     */
    @Autowired
    private EmployeeRepository employeeRepository;

    /**
     * Get all Employees
     *
     * @return {@code List<Employee>} list of Employees
     */
    @Operation(summary = "Get all Employees")
    @GetMapping("/employee")//TODO pagning
    public List<Employee> all() {
        return employeeRepository.findAll();
    }

    /**
     * Get Employee by id
     *
     * @param id Employee id
     * @return {@code Employee} Employee entity
     */
    @GetMapping("/employee/{id}")
    @Operation(summary = "Get Employee by id")
    public Employee getEmployeeByid(@PathVariable String id) {
        return employeeRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "entity not found"
                ));
    }

    /**
     * Create new  Employee
     *
     * @param newEmployee new Employee object
     * @return {@code Employee} object
     */
    @PostMapping("/employee")
    @Operation(summary = "Create new  Employee")
    public Employee newEmployee(@RequestBody Employee newEmployee) {
        newEmployee.setId(null);//ensure create
        employeeRepository.findByLastNameAndFirstName(newEmployee.getFirstName(), newEmployee.getLastName());
        var employee =  employeeRepository.save(newEmployee);
        notificationWebSocketService.sendUpdatedUserId(employee.getId());
        return employee;
    }

    /**
     * Edit  Employee
     * @param id          Employee id
     * @param newEmployee modified Employee
     */
    @PostMapping("/employee/{id}")
    @Operation(summary = "Edit  Employee")
    @Transactional
    public void editEmployee(@PathVariable String id, @RequestBody Employee newEmployee) {
        //check if id exist
        employeeRepository
                .findById(id)
                .ifPresentOrElse(Employee -> employeeRepository.save(newEmployee),
                        () -> {
                            throw new ResponseStatusException(
                                    HttpStatus.NOT_FOUND, "entity not found"
                            );
                        });
        notificationWebSocketService.sendUpdatedUserId(id);
    }

    /**
     * Delete Employee
     * @param id Employee's id
     */
    @Operation(summary = "Delete Employee")
    @DeleteMapping("/employee/{id}")
    public void deleteEmployee(@PathVariable String id) {
        employeeRepository
                .findById(id)
                .ifPresentOrElse(employee -> employeeRepository.delete(employee),
                        () -> {
                            throw new ResponseStatusException(
                                    HttpStatus.NOT_FOUND, "entity not found"
                            );
                        });
        notificationWebSocketService.sendUpdatedUserId(id);
    }

    /**
     * Create Employee task
     *
     * @param id      emploeyr id
     * @param newTask task object
     */
    @Operation(summary = "Create Employee task")
    @PostMapping("/employee/{id}/task")
    public void createEmployeeTask(@PathVariable String id, @RequestBody Task newTask) {
        employeeRepository
                .findById(id)
                .ifPresentOrElse(employee -> {
                            employee.addTask(newTask);
                            employeeRepository.save(employee);
                        },
                        () -> {
                            throw new ResponseStatusException(
                                    HttpStatus.NOT_FOUND, "entity not found"
                            );
                        });
        notificationWebSocketService.sendUpdatedUserId(id);
    }

    /**
     * Delete task from Employee
     *
     * @param id     Employee id
     * @param taskId task id
     */
    @Operation(summary = "Delete task from Employee")
    @DeleteMapping("/employee/{id}/task/{taskId}")
    public void deleteEmployeeTask(@PathVariable String id, @PathVariable String taskId) {
        employeeRepository
                .findById(id)
                .ifPresentOrElse(employee -> {
                            var task = new Task();
                            task.setId(taskId);
                            employee.deleteTask(task);
                            employeeRepository.save(employee);
                        },
                        () -> {
                            throw new ResponseStatusException(
                                    HttpStatus.NOT_FOUND, "entity not found"
                            );
                        });
        notificationWebSocketService.sendUpdatedUserId(id);
    }


    /**
     * Edit Employee task
     * @param id     Employee id
     * @param taskId task id
     * @param task   modified task
     */
    @Operation(summary = "Edit Employee task")
    @PostMapping("/employee/{id}/task/{taskId}")
    public void editEmployeeTask(@PathVariable String id, @PathVariable String taskId, @RequestBody Task task) {
        employeeRepository
                .findById(id)
                .ifPresentOrElse(employee -> {
                            task.setId(taskId);//ensure filled field
                            employee.deleteTask(task);
                            employee.addTask(task);
                            employeeRepository.save(employee);
                        },
                        () -> {
                            throw new ResponseStatusException(
                                    HttpStatus.NOT_FOUND, "entity not found"
                            );
                        });
        notificationWebSocketService.sendUpdatedUserId(id);
    }
}
