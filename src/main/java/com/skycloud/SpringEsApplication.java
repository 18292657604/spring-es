package com.skycloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
//@ComponentScan(basePackages = {"com.skycloud.mapper"})
public class SpringEsApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringEsApplication.class, args);
	}

}

