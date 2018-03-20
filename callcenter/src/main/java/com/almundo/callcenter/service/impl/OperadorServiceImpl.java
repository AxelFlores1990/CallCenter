package com.almundo.callcenter.service.impl;

import java.util.Collections;
import java.util.List;
import java.util.Random;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.almundo.callcenter.controller.request.Call;
import com.almundo.callcenter.domain.Operador;
import com.almundo.callcenter.repository.OperadorRepository;
import com.almundo.callcenter.service.OperadorService;

/**
 * @author axel.flores
 */
@Service
public class OperadorServiceImpl implements OperadorService {

	/**
	 * Logger for Class.
	 */
	private static final Logger logger = LoggerFactory.getLogger(OperadorServiceImpl.class);

	/**
	 * Operadores asignados al area.
	 */
	private List<Operador> ops;
	
	/**
	 * @param repo - Entity Repository.
	 */
	@Autowired
	public OperadorServiceImpl(OperadorRepository repo) {
		this.ops = Collections.synchronizedList(repo.findAll());
	}
	
	/* (non-Javadoc)
	 * @see com.almundo.callcenter.service.impl.CallHandlerService#getQuantityAvailable()
	 */
	@Override
	public long getQuantityAvailable() {
		return this.ops.stream().filter(Operador::isntOccupied).count();
	}
	
	/* (non-Javadoc)
	 * @see com.almundo.callcenter.service.impl.CallHandlerService#dispatchCall(com.almundo.callcenter.controller.request.Call)
	 */
	@Override
	public void processCall(Call call) {
		Operador op = this.ops.stream().filter(Operador::isntOccupied).findFirst().get();
		
		op.setOccupied(true);
		
		try {
			logger.info(" ");
			logger.info("El {} {} esta atendiendo la llamada {} del numero {}.", 
					op.getNombre(), op.getApellido(), call.getTransmitterName(), call.getTransmitterPhone());
			
			Thread.sleep((new Random().nextInt(5) + 5) * 1000);
			
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		op.setOccupied(false);
	}
}