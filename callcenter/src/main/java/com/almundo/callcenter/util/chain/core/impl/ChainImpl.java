package com.almundo.callcenter.util.chain.core.impl;

import com.almundo.callcenter.util.chain.core.Chain;
import com.almundo.callcenter.util.chain.core.Handler;

/**
 * @author axel.flores
 *
 * @param <T> - Object to proccess by the chain.
 */
public class ChainImpl<T> implements Chain<T> {
	
	/**
	 * First element of the chain.
	 */
	private final Handler<T> first;

	/**
	 * @param set first element of the chain.
	 */
	public ChainImpl(Handler<T> first) {
		this.first = first;
	}

	/* (non-Javadoc)
	 * @see com.almundo.callcenter.util.chain.Chain#handle(java.lang.Object)
	 */
	@Override
	public void handle(T t) {
		this.first.handle(t);
	}
}