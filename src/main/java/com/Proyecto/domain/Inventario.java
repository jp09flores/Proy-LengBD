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
@Table(name="inventario")
public class Inventario implements Serializable{
    
    private static final long serialVersionUID =1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_producto")
    private Long idProducto;
    private Long idTipoProducto;
    private String nombre;
    private String fechaIngre;
    private Integer stock;
    private String detalles;
    
    
    public Inventario() {
    }

    public Inventario(Long idProducto, Long idTipoProducto, String nombre, String fechaIngre, Integer stock, String detalles) {
        this.idProducto = idProducto;
        this.idTipoProducto = idTipoProducto;
        this.nombre = nombre;
        this.fechaIngre = fechaIngre;
        this.stock = stock;
        this.detalles = detalles;
    }

   
    
}
