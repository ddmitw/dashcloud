package com.ddmit.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;


@EnableEurekaClient
@EnableZuulProxy
@SpringBootApplication(scanBasePackages = {"com.ddmit.gateway", "com.ddmit.auth"})
public class DashGatewayApplication {

    public static void main(String[] args) {
        SpringApplication.run(DashGatewayApplication.class, args);
    }

}
