package com.almundo.callcenter.service.impl;

import java.util.List;
import java.util.Random;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.almundo.callcenter.controller.request.Call;
import com.almundo.callcenter.domain.Supervisor;
import com.almundo.callcenter.repository.SupervisorRepository;
import com.almundo.callcenter.service.SupervisorService;

/**
 * @author axel.flores
 */
@Service
public class SupervisorServiceImpl implements SupervisorService {

	/**
	 * Logger for Class.
	 */
	private static final Logger logger = LoggerFactory.getLogger(SupervisorServiceImpl.class);
	
	/**
	 * Supervidores asignados al area.
	 */
	private List<Supervisor> sups;
	
	/**
	 * @param repo - Entity Repository.
	 */
	@Autowired
	public SupervisorServiceImpl(SupervisorRepository repo) {
		this.sups = repo.findAll();
	}
	
	/* (non-Javadoc)
	 * @see com.almundo.callcenter.service.impl.CallHandlerService#getQuantityAvailable()
	 */
	@Override
	public long getQuantityAvailable() {
		return this.sups.stream().filter(Supervisor::isntOccupied).count();
	}
	
	/* (non-Javadoc)
	 * @see com.almundo.callcenter.service.impl.CallHandlerService#dispatchCall(com.almundo.callcenter.controller.request.Call)
	 */
	@Override
	public void processCall(Call call) {
		Supervisor sup = this.sups.stream().filter(Supervisor::isntOccupied).findFirst().get();
		
		sup.setOccupied(true);
		
		try {
			logger.info(" ");
			logger.info("El {} {} esta atendiendo la llamada {} del numero {}.", 
					sup.getNombre(), sup.getApellido(), call.getTransmitterName(), call.getTransmitterPhone());
			
			Thread.sleep((new Random().nextInt(5) + 5) * 1000);
			
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		sup.setOccupied(false);
	}
}