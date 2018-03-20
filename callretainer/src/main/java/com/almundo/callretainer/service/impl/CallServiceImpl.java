package com.almundo.callretainer.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;

import com.almundo.callretainer.domain.Call;
import com.almundo.callretainer.service.CallService;

/**
 * @author axel.flores
 */
@Service
public class CallServiceImpl implements CallService {

	/** */
	private static final Logger log = LoggerFactory.getLogger(CallServiceImpl.class);

	/**
	 * Url para consultar cantidad de empleados disponibles.
	 */
	@Value("${url.callcenter.empleados}")
	private String empleadosCountUrl;
	
	/**
	 * Url para enviar llamada retenida al callcenter.
	 */
	@Value("${url.callcenter.call}")
	private String sendCallUrl;
	
	/**
	 * Procesa la llamada retenida.
	 * 
	 * @param call
	 */
	public boolean processRetainedCall(Call call) {
		log.info("processRetainedCall --- Inicio.");

		boolean sended = false;
		
		if (this.existDisoccupied()) {
			this.sendCallToCenter(call);
			sended = true;
		}
		
		log.info("processRetainedCall --- Fin.");
		
		return sended;
	}
	
	/**
	 * Verifica si existen empleados disponibles en el callcenter.
	 * 
	 * @return boolean - existen empleados disponibles.
	 */
	private boolean existDisoccupied() {
		log.info("existDisoccupied --- Inicio.");

		Long quantity = WebClient.create(this.empleadosCountUrl).get().accept(MediaType.APPLICATION_JSON)
				.retrieve().bodyToMono(Long.class).block();
		
		log.info("existDisoccupied --- Fin.");

		return quantity > 0 ? true : false;
	}
	
	/**
	 * Envia la llamada al callcenter
	 * 
	 * @param call - llamada retenida a enviar.
	 */
	private void sendCallToCenter(Call call) {
		log.info("sendCallToCenter --- Inicio.");

		WebClient.create(this.empleadosCountUrl).post().body(BodyInserters.fromObject(call));
		
		log.info("sendCallToCenter --- Fin.");
	}
}