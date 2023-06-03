package com.test.server;

import com.test.server.entity.Employee;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import java.util.Set;

@SpringBootApplication
@EnableMongoRepositories
@OpenAPIDefinition(info = @Info(title = "Employees API", version = "2.0", description = "Employees Information"))

public class TestServerApplication {
    private static Logger logger = LoggerFactory.getLogger(TestServerApplication.class);

    @Autowired
    TestService testService;

    public static void main(String[] args) {
        SpringApplication.run(TestServerApplication.class, args);

    }

    /**
     * test data autofill, remove later
     */
    @EventListener(ApplicationReadyEvent.class)
    public void doSomethingAfterStartup() {
        try {

            try {


                var empl = Employee.builder()
                        .firstName("Bob")
                        .lastName("Salevam")
                        .position("HR")
                        .photo(getClass().getClassLoader().getResourceAsStream("testpics/pic.jpg").readAllBytes())
                                .build();

                testService.getEmployeeRepository().save(empl);
                var manager = Employee.builder()
                        .firstName("Mick")
                        .lastName("Laternoon")
                        .position("CTO")
                        .photo(getClass().getClassLoader().getResourceAsStream("testpics/pic1.jpg").readAllBytes())
                                .employees(Set.of(empl))
                                .build();
                testService.getEmployeeRepository().save(manager);
            } catch (Exception exception) {
                logger.error("Error:", exception);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
