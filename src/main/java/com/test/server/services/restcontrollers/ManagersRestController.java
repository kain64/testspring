package com.test.server.services.restcontrollers;


import com.test.server.entity.Employee;
import com.test.server.entity.Report;
import com.test.server.repositories.EmployeeRepository;
import com.test.server.services.ws.NotificationWebSocketService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
public class ManagersRestController {
    /**
     * managers mongo repository
     */
    @Autowired
    private EmployeeRepository employeeRepository;
    /**
     * notification service
     */
    @Autowired
    private NotificationWebSocketService notificationWebSocketService;


    /**
     * Create Employee task
     *
     * @param id      emploeyr id
     * @param report task object
     */
    @Operation(summary = "Create Employee task")
    @PostMapping("/managers/{id}/report")
    public void createEmployeeTask(@PathVariable String id, @RequestBody Report report) {
        employeeRepository
                .findById(id.toString())
                .ifPresentOrElse(managers -> {

                            managers.addReport(report);
                            employeeRepository.save(managers);
                        },
                        () -> {
                            throw new ResponseStatusException(
                                    HttpStatus.NOT_FOUND, "entity not found"
                            );
                        });
        notificationWebSocketService.sendUpdatedUserId(id);
    }

    /**
     * Delete report from managers
     *
     * @param id     managers id
     * @param taskId task id
     */
    @Operation(summary = "Delete report from managers")
    @DeleteMapping("/managers/{id}/report/{taskId}")
    public void deletemanagersTask(@PathVariable String id, @PathVariable String taskId) {
        employeeRepository
                .findById(id)
                .ifPresentOrElse(managers -> {
                            var report = new Report();
                            report.setId(taskId);
                            managers.deleteReport(report);
                            employeeRepository.save(managers);
                        },
                        () -> {
                            throw new ResponseStatusException(
                                    HttpStatus.NOT_FOUND, "entity not found"
                            );
                        });
        notificationWebSocketService.sendUpdatedUserId(id);
    }

    /**
     * add Employee to manager
     *
     * @param id      emploeyr id
     * @param employee employee object
     */
    @Operation(summary = "add Employee to manager")
    @PostMapping("/managers/{id}/employee")
    public void addEmployeetoEmployee(@PathVariable String id, @RequestBody Employee employee) {
        employeeRepository
                .findById(id.toString())
                .ifPresentOrElse(managers -> {
                            managers.addEmployee(employee);
                            employeeRepository.save(managers);
                        },
                        () -> {
                            throw new ResponseStatusException(
                                    HttpStatus.NOT_FOUND, "entity not found"
                            );
                        });
        notificationWebSocketService.sendUpdatedUserId(employee.getId());
        notificationWebSocketService.sendUpdatedUserId(id);
    }

    /**
     * Delete task from managers
     *
     * @param id     managers id
     * @param employeeId employee id
     */
    @Operation(summary = "Delete task from managers")
    @DeleteMapping("/managers/{id}/employee/{taskId}")
    public void deleteEmployeeFromEmployee(@PathVariable String id, @PathVariable String employeeId) {
        employeeRepository
                .findById(id)
                .ifPresentOrElse(managers -> {
                            var employee = Employee.builder().id(employeeId).build();
                            managers.deleteEmployee(employee);
                            employeeRepository.save(managers);
                        },
                        () -> {
                            throw new ResponseStatusException(
                                    HttpStatus.NOT_FOUND, "entity not found"
                            );
                        });
        notificationWebSocketService.sendUpdatedUserId(employeeId);
        notificationWebSocketService.sendUpdatedUserId(id);
    }

    /**
     * Get Employee By Sub Employee
     *
     * @param id employee id
     */
    @Operation(summary = "Get Employee By Sub Employee")
    @GetMapping("/managers/employee/{id}")
    public Employee getEmployeeBySubEmployee(@PathVariable String id){
       return employeeRepository
                .findByEmployeesId(id)
                .orElseThrow(
                        () -> new ResponseStatusException(
                                HttpStatus.NOT_FOUND, "entity not found"
                        ));

    }
}
