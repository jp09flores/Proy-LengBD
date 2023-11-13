/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.Proyecto.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.io.Serializable;
import lombok.Data;

/**
 *
 * @author jp09f
 */
@Data
@Entity
@Table(name = "trabajos")
public class Trabajos implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_TRABAJO")
    private Long idTrabajo;
    private Long idTipoTrabajo;
    private String fecha;
    private Long idCliente;
    private String numPlaca;
    private Integer cantProductos;
    private Long idEmpleado;

    public Trabajos(Long idTrabajo, Long idTipoTrabajo, String fecha, Long idCliente, String numPlaca, Integer cantProductos, Long idEmpleado) {
        this.idTrabajo = idTrabajo;
        this.idTipoTrabajo = idTipoTrabajo;
        this.fecha = fecha;
        this.idCliente = idCliente;
        this.numPlaca = numPlaca;
        this.cantProductos = cantProductos;
        this.idEmpleado = idEmpleado;
    }

    public Trabajos() {
    }
    
    
    
}
