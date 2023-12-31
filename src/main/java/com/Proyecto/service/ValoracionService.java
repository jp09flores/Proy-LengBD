/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.Proyecto.service;

import com.Proyecto.domain.Cursores;
import com.Proyecto.domain.Valoracion;
import java.util.List;

/**
 *
 * @author jp09f
 */
public interface ValoracionService {
   List<Valoracion> getValoraciones();

    Valoracion seleccionarValoracion(Long idValoracion);

    int eliminarValoracion(Long idValoracion);

    void actualizarValoracion(Long idValoracion, Long idCliente, String comentario, Integer valoracion, String fechaEmi);

    void insertarValoracion(Long idValoracion, Long idCliente, String comentario, Integer valoracion, String fechaEmi);

    Long obtenerUltimaValoracion();
    
    public Cursores Cursor();
}
