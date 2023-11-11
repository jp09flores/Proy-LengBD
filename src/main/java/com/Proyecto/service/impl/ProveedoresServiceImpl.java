/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.Proyecto.service.impl;

import com.Proyecto.dao.ProveedoresDao;
import com.Proyecto.domain.Proveedores;
import com.Proyecto.service.ProveedoresService;
import jakarta.persistence.EntityManager;
import jakarta.persistence.ParameterMode;
import jakarta.persistence.StoredProcedureQuery;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ProveedoresServiceImpl implements ProveedoresService {

    @Autowired
    ProveedoresDao proveedoresDao;

    @Override
    @Transactional(readOnly = true)
    public List<Proveedores> getProveedores() {
        List<Proveedores> Proveedores = proveedoresDao.findAll();
        return Proveedores;
    }
    
    @Autowired
    private EntityManager entityManager;
    
    @Override
    public Proveedores SeleccionarProveedor(Long idProveedor) {
        StoredProcedureQuery query = entityManager.createStoredProcedureQuery("seleccionar_proveedor")
                .registerStoredProcedureParameter("p_id_proveedor", Long.class, ParameterMode.IN)
                .registerStoredProcedureParameter("p_nombre", String.class, ParameterMode.OUT)
                .registerStoredProcedureParameter("p_num_telefono", String.class, ParameterMode.OUT)
                .registerStoredProcedureParameter("p_direccion", String.class, ParameterMode.OUT)
                .registerStoredProcedureParameter("p_detalles", String.class, ParameterMode.OUT)
                .setParameter("p_id_proveedor", idProveedor);

        query.execute();

        String nombre = (String) query.getOutputParameterValue("p_nombre");
        String numTelefono = (String) query.getOutputParameterValue("p_num_telefono");
        String direccion = (String) query.getOutputParameterValue("p_direccion");
        String detalles = (String) query.getOutputParameterValue("p_detalles");

        Proveedores proveedor = new Proveedores();
        proveedor.setNombre(nombre);
        proveedor.setNumTelefono(numTelefono);
        proveedor.setDireccion(direccion);
        proveedor.setDetalles(detalles);

        return proveedor;
    }
    @Transactional
    @Override
    public void eliminarProveedor(Long idProveedor) {
        StoredProcedureQuery query = entityManager.createStoredProcedureQuery("eliminar_proveedor")
                .registerStoredProcedureParameter("p_id_proveedor", Long.class, ParameterMode.IN)
                .setParameter("p_id_proveedor", idProveedor);

        query.execute();
    }
    @Transactional
    @Override
     public void actualizarProveedor(Long idProveedor, String nombre, String numTelefono, String direccion, String detalles) {
        StoredProcedureQuery query = entityManager.createStoredProcedureQuery("actualizar_proveedor")
                .registerStoredProcedureParameter("p_id_proveedor", Long.class, ParameterMode.IN)
                .registerStoredProcedureParameter("p_nombre", String.class, ParameterMode.IN)
                .registerStoredProcedureParameter("p_num_telefono", String.class, ParameterMode.IN)
                .registerStoredProcedureParameter("p_direccion", String.class, ParameterMode.IN)
                .registerStoredProcedureParameter("p_detalles", String.class, ParameterMode.IN)
                .setParameter("p_id_proveedor", idProveedor)
                .setParameter("p_nombre", nombre)
                .setParameter("p_num_telefono", numTelefono)
                .setParameter("p_direccion", direccion)
                .setParameter("p_detalles", detalles);

        query.execute();
    }
     @Transactional
     @Override
     public void insertarProveedor(Long idProveedor, String nombre, String numTelefono, String direccion, String detalles) {
        StoredProcedureQuery query = entityManager.createStoredProcedureQuery("insertar_proveedor")
                .registerStoredProcedureParameter("p_id_proveedor", Long.class, ParameterMode.IN)
                .registerStoredProcedureParameter("p_nombre", String.class, ParameterMode.IN)
                .registerStoredProcedureParameter("p_num_telefono", String.class, ParameterMode.IN)
                .registerStoredProcedureParameter("p_direccion", String.class, ParameterMode.IN)
                .registerStoredProcedureParameter("p_detalles", String.class, ParameterMode.IN)
                .setParameter("p_id_proveedor", idProveedor)
                .setParameter("p_nombre", nombre)
                .setParameter("p_num_telefono", numTelefono)
                .setParameter("p_direccion", direccion)
                .setParameter("p_detalles", detalles);

        query.execute();
    }
    @Override
    public Long ObtenerUltimoProveedor() {
        StoredProcedureQuery query = entityManager.createStoredProcedureQuery("ObtenerUltimoProveedor")
                .registerStoredProcedureParameter("p_id_proveedor", Long.class, ParameterMode.OUT);

        query.execute();

        return (Long) query.getOutputParameterValue("p_id_proveedor")+1;
    }
}
