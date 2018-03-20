package com.almundo.callcenter.service.impl;

import java.util.List;
import java.util.Random;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.almundo.callcenter.controller.request.Call;
import com.almundo.callcenter.domain.Director;
import com.almundo.callcenter.repository.DirectorRepository;
import com.almundo.callcenter.service.DirectorService;

/**
 * @author axel.flores
 */
@Service
public class DirectorServiceImpl implements DirectorService {

	/**
	 * Logger for Class.
	 */
	private static final Logger logger = LoggerFactory.getLogger(DirectorServiceImpl.class);

	/**
	 * Supervidores asignados al area.
	 */
	private List<Director> dirs;
	
	/**
	 * @param repo - Entity Repository.
	 */
	@Autowired
	public DirectorServiceImpl(DirectorRepository repo) {
		this.dirs = repo.findAll();
	}
	
	/* (non-Javadoc)
	 * @see com.almundo.callcenter.service.impl.CallHandlerService#getQuantityAvailable()
	 */
	@Override
	public long getQuantityAvailable() {
		return this.dirs.stream().filter(Director::isntOccupied).count();
	}
	
	/* (non-Javadoc)
	 * @see com.almundo.callcenter.service.impl.CallHandlerService#dispatchCall(com.almundo.callcenter.controller.request.Call)
	 */
	@Override
	public void processCall(Call call) {
		Director dir = this.dirs.stream().filter(Director::isntOccupied).findFirst().get();
		
		dir.setOccupied(true);
		
		try {
			logger.info(" ");
			logger.info("El {} {} esta atendiendo la llamada {} del numero {}.", 
					dir.getNombre(), dir.getApellido(), call.getTransmitterName(), call.getTransmitterPhone());
			
			Thread.sleep((new Random().nextInt(5) + 5) * 1000);
			
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		dir.setOccupied(false);
	}
}