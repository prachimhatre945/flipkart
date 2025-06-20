package com.example.flipkart;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
//@EnableCaching
public class FlipkartApplication {

	public static void main(String[] args) {
		SpringApplication.run(FlipkartApplication.class, args);
	}

}
