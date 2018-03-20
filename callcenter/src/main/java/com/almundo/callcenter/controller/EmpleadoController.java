package com.almundo.callcenter.controller;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.stream.Stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.almundo.callcenter.service.DirectorService;
import com.almundo.callcenter.service.OperadorService;
import com.almundo.callcenter.service.SupervisorService;

import reactor.core.publisher.Mono;

/**
 * @author axel.flores
 */
@RestController
@RequestMapping(value = "empleado")
public class EmpleadoController {

	/** */
	private static final Logger logger = LoggerFactory.getLogger(EmpleadoController.class);

	/**
	 * Handler chain of responsability.
	 */
	@Autowired
	private OperadorService opeServ;
	
	/**
	 * Handler chain of responsability.
	 */
	@Autowired
	private SupervisorService supServ;
	
	/**
	 * Handler chain of responsability.
	 */
	@Autowired
	private DirectorService dirServ;
	
	/**
	 * Dispatcher for calls to callcenter.
	 * 
	 * @param call
	 */
	@GetMapping(value = "/count")
	public Mono<Long> count() {
		logger.info("count --- Inicio.");
		
		Long total = null;
		
		CompletableFuture<Long> futureOpes = CompletableFuture.supplyAsync(() -> this.opeServ.getQuantityAvailable());
		CompletableFuture<Long> futureSups = CompletableFuture.supplyAsync(() -> this.supServ.getQuantityAvailable());
		CompletableFuture<Long> futureDirs = CompletableFuture.supplyAsync(() -> this.dirServ.getQuantityAvailable());
		CompletableFuture<Void> allFuture = CompletableFuture.allOf(futureOpes, futureSups, futureDirs);
		
		try {
			allFuture.get();
			total = Stream.of(futureOpes, futureSups, futureDirs)
					.map(CompletableFuture::join).mapToLong(Long::longValue).sum();
			
		} catch (InterruptedException | ExecutionException e) {
			logger.error("Se produjo un error el obtener en forma concurrente la disponibilidad.", e);
		}
		
		logger.info("count --- Fin.");
		
		return Mono.just(total);
	}
}