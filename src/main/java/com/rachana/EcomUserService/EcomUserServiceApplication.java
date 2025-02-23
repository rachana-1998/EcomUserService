package com.rachana.EcomUserService;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class EcomUserServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(EcomUserServiceApplication.class, args);
		List<String> name=new ArrayList<>();
		 List.of("rachana","monu");
		System.out.println(name);
	}

}
