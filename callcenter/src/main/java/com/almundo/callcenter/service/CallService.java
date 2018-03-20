package com.almundo.callcenter.service;

import com.almundo.callcenter.controller.request.Call;

/**
 * @author axel.flores
 */
public interface CallService {
	
	/**
	 * @param call
	 */
	public void proccessCall(Call call);
}