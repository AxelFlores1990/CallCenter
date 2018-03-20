package com.almundo.callcenter.util.chain.call.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.almundo.callcenter.controller.request.Call;
import com.almundo.callcenter.service.DirectorService;
import com.almundo.callcenter.service.OperadorService;
import com.almundo.callcenter.service.SupervisorService;
import com.almundo.callcenter.service.kafka.CallProducer;
import com.almundo.callcenter.util.chain.call.CallChainResp;
import com.almundo.callcenter.util.chain.core.Chain;
import com.almundo.callcenter.util.chain.core.ChainBuilder;

/**
 * @author axel.flores
 */
@Service
public class CallChainRespImpl implements CallChainResp {

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
	 * Producer to send to kafka topic.
	 */
	@Autowired
	private CallProducer producer;
	
	/**
	 * ChainBuilder.
	 */
	@Autowired
	private ChainBuilder<Call> chainBuilder;
	
	/**
	 * @return Chain Responsability of calls.
	 */
	public Chain<Call> responsibilityDelegatorBuilder() {
		Chain<Call> chain = this.chainBuilder
			.first(c -> {
				if (this.opeServ.getQuantityAvailable() > 0) {
					this.opeServ.processCall(c);
					return true;
				}
					return false;

			})
            .successor(c -> {
                if (this.supServ.getQuantityAvailable() > 0) {
                	this.supServ.processCall(c);
                    return true;
                }
                return false;
                
            })
            .successor(c -> {
                if (this.dirServ.getQuantityAvailable() > 0) {
                	this.dirServ.processCall(c);
                    return true;
                }
                return false;
                
            })
            .successor(c -> {
            	this.producer.produce(c);
            	return true;
            	
            }).build();
        
        return chain;
	}
}