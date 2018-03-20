package com.almundo.callcenter.util.chain.core;

/**
 * @author axel.flores
 *
 * @param <T> -  Object to proccess by the chain.
 */
public interface Chain<T> {
	
    /**
     * @param t - element to be processed by the chain.
     */
    void handle(T t);
}