package com.GitLabReviewer.GR;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan
@EnableAutoConfiguration
@SpringBootApplication
public class GrApplication {

	public static void main(String[] args) {
		SpringApplication.run(GrApplication.class, args);
	}

}
