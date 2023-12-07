/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.Proyecto.service;

import com.Proyecto.domain.Inventario;
import java.util.List;

/**
 *
 * @author jp09f
 */
public interface InventarioService {
    List<Inventario> getInventario();

    Inventario seleccionarProducto(Long idProducto);

    int eliminarProducto(Long idProducto);

    void actualizarProducto(Long idProducto, Long idTipoProducto, String nombre, String fechaIngreso, Integer stock, String detalles);

    void insertarProducto(Long idProducto, Long idTipoProducto, String nombre, String fechaIngreso, Integer stock, String detalles);

    Long obtenerUltimoProducto();
}
