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

@Data
@Entity
@Table(name = "VALORACIONES")
public class Valoracion  implements Serializable{
    
    private static final long serialVersionUID =1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_VALORACION")
    private Long idValoracion;

    @Column(name = "ID_CLIENTE")
    private Long idCliente;

    @Column(name = "COMENTARIO")
    private String comentario;

    @Column(name = "VALORACION")
    private Integer valoracion;

    @Column(name = "FECHA_EMI")
    private String fechaEmi;

    public Valoracion(Long idValoracion, Long idCliente, String comentario, Integer valoracion, String fechaEmi) {
        this.idValoracion = idValoracion;
        this.idCliente = idCliente;
        this.comentario = comentario;
        this.valoracion = valoracion;
        this.fechaEmi = fechaEmi;
    }

    public Valoracion() {
    }

    
}
