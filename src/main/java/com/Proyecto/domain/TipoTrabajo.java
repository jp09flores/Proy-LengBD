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
@Table(name="tipo_trabajo")
public class TipoTrabajo {
    
    private static final long serialVersionUID =1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_tipo_trabajo")
    private Long idTipoTrabajo;
    private String nombre;
    private String requisitos;
    private String contenido;
    private String detalles;

    public TipoTrabajo() {
    }

    public TipoTrabajo(Long idTipoTrabajo, String nombre, String requisitos, String contenido, String detalles) {
        this.idTipoTrabajo = idTipoTrabajo;
        this.nombre = nombre;
        this.requisitos = requisitos;
        this.contenido = contenido;
        this.detalles = detalles;
    }
}
