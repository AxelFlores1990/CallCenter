package com.almundo.callretainer.service;

import com.almundo.callretainer.domain.Call;

/**
 * @author axel.flores
 */
public interface CallService {

	/**
	 * Procesa la llamada retenida.
	 * 
	 * @param call
	 */
	public boolean processRetainedCall(Call call);
}