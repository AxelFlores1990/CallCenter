package com.almundo.callcenter.service.kafka.impl;

import java.util.function.Consumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

import com.almundo.callcenter.controller.request.Call;
import com.almundo.callcenter.service.kafka.CallProducer;

/**
 * @author axel.flores
 */
@Service
public class CallProducerImpl implements CallProducer {

	/** */
	private static final Logger log = LoggerFactory.getLogger(CallProducerImpl.class);
	
    /** */
    @Autowired
    private KafkaTemplate<String, Call> kafkaTemplate;
	
    /**
     * @param topic
     * @param call
     */
    public void produce(Call call) {
        this.produce(call,
            r -> log.info("sent message='{}' with offset={}", call, r.getRecordMetadata().offset()),
            t -> log.error("unable to send message='{}'", call, t)
        );
    }

    /**
     * @param topic
     * @param call
     * @param onSuccess
     * @param onError
     */
	public void produce(Call call, Consumer<SendResult<String, Call>> onSuccess, Consumer<Throwable> onError) {
		ListenableFuture<SendResult<String, Call>> promise = this.kafkaTemplate.sendDefault(call);

		promise.addCallback(new ListenableFutureCallback<SendResult<String, Call>>() {

			@Override
			public void onSuccess(SendResult<String, Call> r) {
				onSuccess.accept(r);
			}

			@Override
			public void onFailure(Throwable t) {
				onError.accept(t);
			}
		});
	}
}