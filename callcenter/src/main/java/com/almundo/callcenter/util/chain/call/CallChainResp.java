package com.almundo.callcenter.util.chain.call;

import com.almundo.callcenter.controller.request.Call;
import com.almundo.callcenter.util.chain.core.Chain;

/**
 * @author axel.flores
 */
public interface CallChainResp {

	/**
	 * @return Chain Responsability of calls.
	 */
	public Chain<Call> responsibilityDelegatorBuilder();
}
