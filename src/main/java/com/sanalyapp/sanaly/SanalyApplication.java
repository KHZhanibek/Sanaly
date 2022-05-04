package com.sanalyapp.sanaly;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
public class SanalyApplication {

	public static void main(String[] args) {
		SpringApplication.run(SanalyApplication.class, args);
	}

}