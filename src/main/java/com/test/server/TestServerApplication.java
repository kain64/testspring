package com.test.server;

import com.test.server.entity.Manager;
import com.test.server.repositories.ManagersRepository;
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

import java.util.ArrayList;

@SpringBootApplication
@EnableMongoRepositories
@OpenAPIDefinition(info = @Info(title = "Employees API", version = "2.0", description = "Employees Information"))

public class TestServerApplication {
	private static Logger logger =   LoggerFactory.getLogger(TestServerApplication.class);

	@Autowired
	TestService testService;
	public static void main(String[] args) {
		SpringApplication.run(TestServerApplication.class, args);

	}
	@EventListener(ApplicationReadyEvent.class)
	public void doSomethingAfterStartup() {
		try {
		//	testService.getManagersRepository().save(new Manager(null, "test1", new ArrayList<>()));
		}catch (Exception exception){
			logger.error("Error:", exception);
		}
	}

}
