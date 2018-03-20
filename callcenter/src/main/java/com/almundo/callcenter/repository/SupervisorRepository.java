package com.almundo.callcenter.repository;

import org.springframework.stereotype.Repository;

import com.almundo.callcenter.domain.Supervisor;

/**
 * @author axel.flores
 */
@Repository
public interface SupervisorRepository extends EmpleadoRepository<Supervisor, Long> {

}