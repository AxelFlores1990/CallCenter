package com.almundo.callcenter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.reactive.config.EnableWebFlux;

/**
 * @author axel.flores
 */
@EnableWebFlux
@SpringBootApplication
@EntityScan(basePackages = "com.almundo.callcenter.domain")
@EnableJpaRepositories(basePackages = "com.almundo.callcenter.repository")
public class CallCenterApplication {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		SpringApplication.run(CallCenterApplication.class, args);
	}
}