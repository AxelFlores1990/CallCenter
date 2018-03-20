package com.almundo.callcenter.service;

import com.almundo.callcenter.controller.request.Call;

/**
 * @author axel.flores
 */
public interface DirectorService {

	/**
	 * @param call
	 */
	public void processCall(Call call);
	
	/**
	 * Cantidad de empleados disponibles en el area.
	 * 
	 * @param call
	 */
	long getQuantityAvailable();
}