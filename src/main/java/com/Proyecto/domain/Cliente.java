package com.Proyecto.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.io.Serializable;
import lombok.Data;

@Data
@Entity
@Table(name="clientes")
public class Cliente implements Serializable {
    
    private static final long serialVersionUID =1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_cliente")
    private Long idCliente;
    private String username;
    private String contrasena;
    private String nombre;
    private String apellido;
    private String direccion;
    private String numTelefono;
    private String correoElect;

    public Cliente() {
    }

    public Cliente(Long idCliente, String username, String contrasena, String nombre, String apellido, String direccion, String numTelefono, String correoElect) {
        this.idCliente = idCliente;
        this.username = username;
        this.contrasena = contrasena;
        this.nombre = nombre;
        this.apellido = apellido;
        this.direccion = direccion;
        this.numTelefono = numTelefono;
        this.correoElect = correoElect;
    }
}
