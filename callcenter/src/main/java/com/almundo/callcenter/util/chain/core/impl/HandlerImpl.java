package com.almundo.callcenter.util.chain.core.impl;

import com.almundo.callcenter.util.chain.core.Handler;

/**
 * @author axel.flores
 *
 * @param <T> - Object to proccess by the chain.
 */
public class HandlerImpl<T> implements Handler<T> {
	
	/**
	 * Current handler logic.
	 */
	private final Handler<T> delegate;
	
	/**
	 * Successor in chain.
	 */
	private Handler<T> successor;

	/**
	 * @param delegate - Current handler logic.
	 */
	public HandlerImpl(Handler<T> delegate) {
		this.delegate = delegate;
	}

	/**
	 * @param successor - Set successor in chain.
	 */
	public void setSuccessor(HandlerImpl<T> successor) {
		this.successor = successor;
	}

	/* (non-Javadoc)
	 * @see com.almundo.callcenter.util.chain.core.Handler#handle(java.lang.Object)
	 */
	@Override
	public boolean handle(T t) {
		if (this.delegate.handle(t)) {
			return true;
			
		} else if (successor != null) {
			return successor.handle(t);
		}
		
		return false;
	}
}