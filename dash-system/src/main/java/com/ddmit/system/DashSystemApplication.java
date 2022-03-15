package com.ddmit.system;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@SpringBootApplication
public class DashSystemApplication {

	public static void main(String[] args) {
		SpringApplication.run(DashSystemApplication.class, args);
	}

}
