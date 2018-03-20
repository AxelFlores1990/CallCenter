package com.almundo.callcenter.repository;

import org.springframework.stereotype.Repository;

import com.almundo.callcenter.domain.Operador;

/**
 * @author axel.flores
 */
@Repository
public interface OperadorRepository extends EmpleadoRepository<Operador, Long> {

}