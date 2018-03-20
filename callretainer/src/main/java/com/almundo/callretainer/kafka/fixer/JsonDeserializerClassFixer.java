package com.almundo.callretainer.kafka.fixer;

import org.springframework.kafka.support.serializer.JsonDeserializer;

import com.almundo.callretainer.domain.Call;

/**
 * @author axel.flores
 */
public class JsonDeserializerClassFixer extends JsonDeserializer<Call> {
	public JsonDeserializerClassFixer() {
		super();
	}
}