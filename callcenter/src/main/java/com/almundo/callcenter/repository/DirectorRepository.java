package com.almundo.callcenter.repository;

import org.springframework.stereotype.Repository;

import com.almundo.callcenter.domain.Director;

/**
 * @author axel.flores
 */
@Repository
public interface DirectorRepository extends EmpleadoRepository<Director, Long> {

}