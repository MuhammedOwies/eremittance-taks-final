package com.evision.finance.eremittance.apis;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.bind.annotation.CrossOrigin;

@PropertySource("classpath:application.properties")
//@PropertySource("classpath*:auth-application.properties")
@SpringBootApplication(scanBasePackages = "com.evision.finance.eremittance")
@EnableJpaRepositories(basePackages = "com.evision.finance.eremittance")
@EntityScan(basePackages = "com.evision.finance.eremittance")
@CrossOrigin
public class ApisApplication {
    public static void main(String[] args) {
        SpringApplication.run(ApisApplication.class, args);
    }
}
