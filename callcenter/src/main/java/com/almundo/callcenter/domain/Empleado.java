package com.almundo.callcenter.domain;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

/**
 * @author axel.flores
 */
@Entity
@DiscriminatorColumn(name="cargo")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class Empleado {

	/**
	 * Id del empleado.
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(updatable = false, nullable = false)
	private Long id;
	
	/**
	 * Nombre del empleado.
	 */
	@Column
	private String nombre;
	
	/**
	 * Apellido del empleado.
	 */
	@Column
	private String apellido;

	/**
	 * Si el empleado se encuentra en llamada.
	 */
	@Column(columnDefinition = "boolean default false", nullable = false)
	private boolean occupied;
	
	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @return the nombre
	 */
	public String getNombre() {
		return nombre;
	}

	/**
	 * @param nombre the nombre to set
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	/**
	 * @return the apellido
	 */
	public String getApellido() {
		return apellido;
	}

	/**
	 * @param apellido the apellido to set
	 */
	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	/**
	 * @return the occupied
	 */
	public boolean isOccupied() {
		return occupied;
	}

	/**
	 * @return the occupied
	 */
	public boolean isntOccupied() {
		return !occupied;
	}
	
	/**
	 * @param occupied the occupied to set
	 */
	public void setOccupied(boolean occupied) {
		this.occupied = occupied;
	}
}