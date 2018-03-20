package com.almundo.callcenter.util.chain.core;

/**
 * @author axel.flores
 *
 * @param <T> -  Object to proccess by the chain.
 */
public interface Handler<T> {
	
	/**
	 * @param t - Logic to execute by handler.
	 * 
	 * @return - logic executed.
	 */
	boolean handle(T t);
}