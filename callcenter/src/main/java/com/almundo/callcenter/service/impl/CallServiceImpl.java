package com.almundo.callcenter.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.almundo.callcenter.controller.request.Call;
import com.almundo.callcenter.service.CallService;
import com.almundo.callcenter.util.chain.call.CallChainResp;

/**
 * @author axel.flores
 */
@Service
public class CallServiceImpl implements CallService {

	/** */
	private static final Logger logger = LoggerFactory.getLogger(CallServiceImpl.class);
	
	/**
	 * Cadena de sectores por responsabilidad.
	 */
	@Autowired
	private CallChainResp chain;
	
	/**
	 * @param call
	 */
	public void proccessCall(Call call) {
		logger.info("proccessCall --- Inicio.");
		this.chain.responsibilityDelegatorBuilder().handle(call);
		logger.info("proccessCall --- Fin.");
	}
}