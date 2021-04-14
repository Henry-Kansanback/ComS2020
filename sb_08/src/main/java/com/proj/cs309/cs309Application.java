package com.proj.cs309;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"com.proj.cs309.user"})
public class cs309Application {

	public static void main(String[] args) {
		SpringApplication.run(cs309Application.class, args);
	}
	

}
