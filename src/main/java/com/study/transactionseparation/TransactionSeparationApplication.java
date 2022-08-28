package com.study.transactionseparation;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
public class TransactionSeparationApplication {

	public static void main(String[] args) {
		SpringApplication.run(TransactionSeparationApplication.class, args);
	}

}
