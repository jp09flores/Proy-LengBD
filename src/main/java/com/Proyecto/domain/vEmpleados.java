
package com.Proyecto.domain;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.io.Serializable;
import lombok.Data;


@Data
@Entity
@Table(name="VISTA_EMPLEADOS")
public class vEmpleados implements Serializable{
 private static final long serialVersionUID =1L;
    @Id
    
    private Long idEmpleado;
    private String nombre;
    private String apellido;
    private double salario;
}
