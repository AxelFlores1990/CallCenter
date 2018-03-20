package com.almundo.callcenter.controller;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebFlux;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.reactive.function.client.WebClient;

import com.almundo.callcenter.CallCenterApplication;

import reactor.core.publisher.Mono;

/**
 * @author axel.flores
 */
@RunWith(SpringRunner.class)
@AutoConfigureWebFlux
@TestPropertySource(locations = "classpath:application.yml")
@ComponentScan(basePackages = "com.almundo.callcenter")
@SpringBootTest(webEnvironment = WebEnvironment.DEFINED_PORT, classes = CallCenterApplication.class)
public class EmpleadoControllerTest {

	/**
	 * Logger For Class.
	 */
	private Logger log = LoggerFactory.getLogger(EmpleadoControllerTest.class);

	/**
	 * @throws Exception
	 */
	@Test
	public void getCount() throws Exception {
		log.info("getCount --- Inicio.");

		Mono<Long> resp = WebClient.builder().baseUrl("http://localhost:8080").build().get().uri("/empleado/count")
				.retrieve().bodyToMono(Long.class);
		
		assertEquals(Long.valueOf(16), resp.block());
		
		log.info("getCount --- Fin.");
	}
}
