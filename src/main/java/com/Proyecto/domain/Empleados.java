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
@Table(name="EMPLEADOS")
public class Empleados implements Serializable {
private static final long serialVersionUID =1L;
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
@Column(name="ID_EMPLEADO") 
private Long IdEmpleado;
private String nombre;
private String apellido;
private String direccion;
private String numTelefono;
private String correo;
private String salario;
private String FechaIngre;
private String FechaNac;

public Empleados() {
}

    public Empleados(Long IdEmpleado, String nombre, String apellido, String direccion, String numTelefono, String correo, String salario, String FechaIngre, String FechaNac) {
        this.IdEmpleado = IdEmpleado;
        this.nombre = nombre;
        this.apellido = apellido;
        this.direccion = direccion;
        this.numTelefono = numTelefono;
        this.correo = correo;
        this.salario = salario;
        this.FechaIngre = FechaIngre;
        this.FechaNac = FechaNac;
    }






}


