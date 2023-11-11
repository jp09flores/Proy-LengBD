/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.Proyecto.service;

import com.Proyecto.domain.Productos;
import java.util.List;

/**
 *
 * @author micro
 */
public interface ProductosService {
public List<Productos> getProductos();
     
public Productos SeleccionarProductos(Long IdTipoProducto);
public void eliminarTipoProducto(Long IdTipoProducto);
public void actualizarProductos(Long IdTipoProducto, String nombre,   String detalles);
public void insertarProductos(Long IdTipoProducto, String nombre,   String detalles);
public Long ObtenerUltimoProducto();  
}
