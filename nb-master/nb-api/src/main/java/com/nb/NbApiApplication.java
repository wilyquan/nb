package com.nb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.ComponentScan;

//@SpringBootApplication(scanBasePackages = {"com.nb"})
//@ComponentScan(basePackages={"com"})
@SpringBootApplication
@EnableCaching
public class NbApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(NbApiApplication.class, args);
	}
}
