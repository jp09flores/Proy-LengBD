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
@Table(name="vehiculos")
public class Vehiculo {
    
    private static final long serialVersionUID =1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_tipo_trabajo")
    private String numPlaca;
    private String numMotor;
    private String marca;
    private String color;
    private String modelo;
    private String year;

    public Vehiculo() {
    }

    public Vehiculo(String numPlaca, String numMotor, String marca, String color, String modelo, String year) {
        this.numPlaca = numPlaca;
        this.numMotor = numMotor;
        this.marca = marca;
        this.color = color;
        this.modelo = modelo;
        this.year = year;
    }
}