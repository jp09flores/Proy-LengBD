/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.Proyecto.domain;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.io.Serializable;
import lombok.Data;
/**
 *
 * @author Diana
 */
@Data
@Entity
@Table(name="VISTA_VALORACIONES_BASICA")
public class vValoracion  implements Serializable {
  private static final long serialVersionUID =1L;
    @Id
   private Long idValoracion;
    private Long idCliente;
    private String comentario;
}

