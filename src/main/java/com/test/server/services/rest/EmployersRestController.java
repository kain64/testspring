package com.test.server.services.rest;

import com.test.server.entity.Employee;
import com.test.server.entity.Task;
import com.test.server.repositories.EmployeeRepository;
import io.swagger.v3.oas.annotations.Operation;
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
     * Employee mongo repository
     */
    @Autowired
    private EmployeeRepository EmployeeRepository;

    /**
     * Get all Employees
     *
     * @return {@code List<Employee>} list of Employees
     */
    @Operation(summary = "Get all Employees")
    @GetMapping("/Employee")
    public List<Employee> all() {
        return EmployeeRepository.findAll();
    }

    /**
     * Get Employee by id
     *
     * @param id Employee id
     * @return {@code Employee} Employee entity
     */
    @GetMapping("/Employee/{id}")
    @Operation(summary = "Get Employee by id")
    public Employee getEmployeeByid(@PathVariable String id) {
        return EmployeeRepository.findById(id.toString())
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "entity not found"
                ));
    }

    /**
     * Create new  Employee
     *
     * @param newEmployee
     * @return {@code Employee} object
     */
    @PostMapping("/Employee")
    @Operation(summary = "Create new  Employee")
    public Employee newEmployee(@RequestBody Employee newEmployee) {
        return EmployeeRepository.save(newEmployee);
    }

    /**
     * Edit new  Employee
     * @param id          Employee id
     * @param newEmployee modified Employee
     */
    @PostMapping("/Employee/{id}")
    @Operation(summary = "Edit new  Employee")
    @Transactional
    public void editEmployee(@PathVariable String id, @RequestBody Employee newEmployee) {
        //check if id exist
        EmployeeRepository
                .findById(id.toString())
                .ifPresentOrElse(Employee -> EmployeeRepository.save(newEmployee),
                        () -> {
                            throw new ResponseStatusException(
                                    HttpStatus.NOT_FOUND, "entity not found"
                            );
                        });
        //save
    }

    /**
     * Delete Employee
     * @param id Employee's id
     */
    @Operation(summary = "Delete Employee")
    @DeleteMapping("/Employee/{id}")
    public void deleteEmployee(@PathVariable String id) {
        EmployeeRepository
                .findById(id.toString())
                .ifPresentOrElse(Employee -> EmployeeRepository.delete(Employee),
                        () -> {
                            throw new ResponseStatusException(
                                    HttpStatus.NOT_FOUND, "entity not found"
                            );
                        });
    }

    /**
     * Create Employee task
     *
     * @param id      emploeyr id
     * @param newTask task object
     */
    @Operation(summary = "Create Employee task")
    @PostMapping("/Employee/{id}/task")
    public void createEmployeeTask(@PathVariable String id, @RequestBody Task newTask) {
        EmployeeRepository
                .findById(id.toString())
                .ifPresentOrElse(Employee -> {
                            Employee.addTask(newTask);
                            EmployeeRepository.save(Employee);
                        },
                        () -> {
                            throw new ResponseStatusException(
                                    HttpStatus.NOT_FOUND, "entity not found"
                            );
                        });
    }

    /**
     * Delete task from Employee
     *
     * @param id     Employee id
     * @param taskId task id
     */
    @Operation(summary = "Delete task from Employee")
    @DeleteMapping("/Employee/{id}/task/{taskId}")
    public void deleteEmployeeTask(@PathVariable String id, @PathVariable String taskId) {
        EmployeeRepository
                .findById(id.toString())
                .ifPresentOrElse(Employee -> {
                            var task = new Task();
                            task.setId(taskId);
                            Employee.deleteTask(task);
                            EmployeeRepository.save(Employee);
                        },
                        () -> {
                            throw new ResponseStatusException(
                                    HttpStatus.NOT_FOUND, "entity not found"
                            );
                        });
    }


    /**
     * Edit Employee task
     * @param id     Employee id
     * @param taskId task id
     * @param task   modified task
     */
    @Operation(summary = "Edit Employee task")
    @PostMapping("/Employee/{id}/task/{taskId}")
    public void editEmployeeTask(@PathVariable String id, @PathVariable String taskId, @RequestBody Task task) {
        EmployeeRepository
                .findById(id.toString())
                .ifPresentOrElse(Employee -> {
                            task.setId(taskId);//ensure filled field
                            Employee.deleteTask(task);
                            Employee.addTask(task);
                            EmployeeRepository.save(Employee);
                        },
                        () -> {
                            throw new ResponseStatusException(
                                    HttpStatus.NOT_FOUND, "entity not found"
                            );
                        });
    }
}
