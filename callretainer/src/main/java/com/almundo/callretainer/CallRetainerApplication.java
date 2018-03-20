package com.almundo.callretainer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.annotation.EnableKafka;

/**
 * @author axel.flores
 */
@EnableKafka
@SpringBootApplication
public class CallRetainerApplication {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		SpringApplication.run(CallRetainerApplication.class, args);
	}
}
