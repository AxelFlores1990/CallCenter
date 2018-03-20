package com.almundo.callcenter.repository;

import java.util.List;

import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.Repository;

/**
 * @author axel.flores
 */
@NoRepositoryBean
public interface EmpleadoRepository<T, ID> extends Repository<T, ID> {

	/**
	 * Returns all instances of the type.
	 * 
	 * @return all entities
	 */
	List<T> findAll();
}