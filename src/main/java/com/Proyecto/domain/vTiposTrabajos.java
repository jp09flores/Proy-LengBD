
package com.Proyecto.domain;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.io.Serializable;
import lombok.Data;


@Data
@Entity
@Table(name = "VISTA_TIPOS_TRABAJOS")
public class vTiposTrabajos implements Serializable {
 private static final long serialVersionUID =1L;
    @Id
    private Long IdTipoTrabajo;
    private String nombre;
    private String requisitos;
    private String contenido;
}
