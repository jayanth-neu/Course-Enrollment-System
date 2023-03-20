package com.esdfinal.enroll;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan({"com.esdfinal.controller", "com.esdfinal.dao", "com.esdfinal.pojo"})
public class EnrollApplication {

	public static void main(String[] args) {
		SpringApplication.run(EnrollApplication.class, args);
	}

}
