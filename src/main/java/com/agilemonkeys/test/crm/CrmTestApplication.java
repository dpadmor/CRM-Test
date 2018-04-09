package com.agilemonkeys.test.crm;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan
public class CrmTestApplication {

	public static void main(String[] args) {
		SpringApplication.run(CrmTestApplication.class, args);
	}
}
