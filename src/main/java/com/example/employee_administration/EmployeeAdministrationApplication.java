package com.example.employee_administration;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"org.springframework.security.crypto.password.PasswordEncoder"})
public class EmployeeAdministrationApplication {

	public static void main(String[] args) {
		SpringApplication.run(EmployeeAdministrationApplication.class, args);
	}

}
