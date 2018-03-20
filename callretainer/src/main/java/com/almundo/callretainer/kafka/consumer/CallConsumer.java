package com.almundo.callretainer.kafka.consumer;

import java.util.concurrent.CountDownLatch;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Service;

import com.almundo.callretainer.domain.Call;
import com.almundo.callretainer.service.CallService;

/**
 * @author axel.flores
 */
@Service
public class CallConsumer {

	/** */
	private static final Logger log = LoggerFactory.getLogger(CallConsumer.class);
	
	/** */
	@Autowired
	private CallService callServ;

	/** */
	final private CountDownLatch latch = new CountDownLatch(1);
	
	/**
	 * @param payload
	 */
	@KafkaListener(topics = "${kafka.default-topic}")
	public void consume(Call call, Acknowledgment ack) {
		log.info(" -- consume --- Inicio.");
		log.info("received call='{}'", call.toString());
		
		boolean sended = this.callServ.processRetainedCall(call); 
		
		if (sended) {
			this.latch.countDown();
			ack.acknowledge();
		}
		
		log.info(" -- consume --- Fin.");
	}
}