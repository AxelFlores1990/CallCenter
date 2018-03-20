package com.almundo.callcenter.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.almundo.callcenter.controller.request.Call;
import com.almundo.callcenter.service.CallService;

import reactor.core.publisher.Mono;

/**
 * @author axel.flores
 */
@RestController
@RequestMapping(value = "call")
public class CallController {

	/** */
	private static final Logger logger = LoggerFactory.getLogger(CallController.class);

	/**
	 * Service of controller entity.
	 */
	@Autowired
	private CallService serv;
	
	/**
	 * Dispatcher for calls to callcenter.
	 * 
	 * @param call
	 */
	@PostMapping
	@ResponseStatus(code = HttpStatus.ACCEPTED)
	public Mono<Void> dispatchCall(@RequestBody Call call) {
		logger.info("dispatchCall --- Inicio.");
		
		this.serv.proccessCall(call);
		
		logger.info("dispatchCall --- Fin.");
		
		return Mono.empty();
	}
}