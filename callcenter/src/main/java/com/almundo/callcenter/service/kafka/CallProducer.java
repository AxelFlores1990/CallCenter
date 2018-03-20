package com.almundo.callcenter.service.kafka;

import com.almundo.callcenter.controller.request.Call;

/**
 * @author axel.flores
 */
public interface CallProducer {
	
    /**
     * @param topic
     * @param call
     */
    public void produce(Call call);
}