package com.almundo.callcenter.domain;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 * @author axel.flores
 */
@Entity
@DiscriminatorValue("Operador")
public class Operador extends Empleado {

}