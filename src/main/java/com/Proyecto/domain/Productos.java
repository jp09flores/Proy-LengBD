
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
@Table(name="TIPO_PRODUCTO")
public class Productos implements Serializable {
 private static final long serialVersionUID =1L;
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name=" ID_TIPO_PRODUCTO")
  private Long IdTipoProducto;
  private String nombre;
  private String detalles;

    public Productos() {
    }

    public Productos(Long IdTipoProducto, String nombre, String detalles) {
        this.IdTipoProducto = IdTipoProducto;
        this.nombre = nombre;
        this.detalles = detalles;
    }
  
}
