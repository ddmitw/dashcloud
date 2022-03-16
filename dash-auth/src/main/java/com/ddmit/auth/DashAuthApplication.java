package com.ddmit.auth;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@SpringBootApplication
public class DashAuthApplication {

	public static void main(String[] args) {
		SpringApplication.run(DashAuthApplication.class, args);
	}

}
