package com.UploadCSV;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class UploadCsvFileApplication {

	public static void main(String[] args) {
		System.out.println("Started Loading");
		SpringApplication.run(UploadCsvFileApplication.class, args);
		System.out.println("Loading done");
	}

}
