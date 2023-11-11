/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.Proyecto.service;
import com.Proyecto.domain.Proveedores;
import java.util.List;

public interface ProveedoresService {
public List<Proveedores> getProveedores();
     
public Proveedores SeleccionarProveedor(Long idProveedor);
public void eliminarProveedor(Long idProveedor);
public void actualizarProveedor(Long idProveedor, String nombre, String numTelefono, String direccion, String detalles);
public void insertarProveedor(Long idProveedor, String nombre, String numTelefono, String direccion, String detalles);
public Long ObtenerUltimoProveedor();
}
