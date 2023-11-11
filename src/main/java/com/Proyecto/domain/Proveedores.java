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
 * @author micro
 */
@Data
@Entity
@Table(name="proveedores")
public class Proveedores implements Serializable{
  private static final long serialVersionUID =1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_proveedor")
    private Long idProveedor;
    private String nombre;
    private String numTelefono;
    private String direccion;
    private String detalles;

    public Proveedores() {
    }

    public Proveedores(Long idProveedor, String nombre, String numTelefono, String direccion, String detalles) {
        this.idProveedor = idProveedor;
        this.nombre = nombre;
        this.numTelefono = numTelefono;
        this.direccion = direccion;
        this.detalles = detalles;
    }
    
   
}
