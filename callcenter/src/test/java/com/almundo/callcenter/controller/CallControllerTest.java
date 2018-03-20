package com.almundo.callcenter.controller;

import java.util.List;
import java.util.concurrent.ForkJoinPool;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebFlux;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.reactive.function.client.WebClient;

import com.almundo.callcenter.CallCenterApplication;
import com.almundo.callcenter.controller.request.Call;

import reactor.core.publisher.Mono;

/**
 * @author axel.flores
 */
@RunWith(SpringRunner.class)
@AutoConfigureWebFlux
@TestPropertySource(locations = "classpath:application.yml")
@SpringBootTest(webEnvironment = WebEnvironment.DEFINED_PORT, classes = CallCenterApplication.class)
public class CallControllerTest {

	/** */
	private static final String CONST_SV_BASE_URL = "http://localhost:8080";
	
	/**
	 * Logger For Class.
	 */
	private Logger log = LoggerFactory.getLogger(CallControllerTest.class);
	
	/**
	 * @throws Exception
	 */
	@Test
	public void dispatchCall() throws Exception {
		log.info("dispatchCall --- Inicio.");

		List<WebClient> webCliList = IntStream.range(0, 10)
				.mapToObj(i -> WebClient.builder().baseUrl(CONST_SV_BASE_URL).build())
				.collect(Collectors.toList());
		
		List<Mono<Void>> resps = webCliList.parallelStream()
				.map(w -> w.post().uri("/call").contentType(MediaType.APPLICATION_JSON)
						.body(Mono.just(new Call("123", String.valueOf(System.currentTimeMillis()))), Call.class)
						.retrieve().bodyToMono(Void.class))
				.collect(Collectors.toList());
		
		new ForkJoinPool(10).submit(() -> resps.parallelStream().map(Mono::block).collect(Collectors.toList())).get();
		
		log.info("dispatchCall --- Fin.");
	}
}