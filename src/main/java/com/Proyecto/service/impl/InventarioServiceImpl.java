/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.Proyecto.service.impl;

import com.Proyecto.dao.InventarioDao;
import com.Proyecto.domain.Inventario;
import com.Proyecto.service.InventarioService;
import jakarta.persistence.EntityManager;
import jakarta.persistence.ParameterMode;
import jakarta.persistence.StoredProcedureQuery;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author jp09f
 */
@Service
public class InventarioServiceImpl implements InventarioService {

    @Autowired
    InventarioDao inventarioDao;
    @Autowired
    private EntityManager entityManager;

    @Override
    @Transactional(readOnly = true)
    public List<Inventario> getInventario() {
        List<Inventario> inventario = inventarioDao.findAll();
        return inventario;
    }

    @Override
    public Inventario seleccionarProducto(Long idProducto) {
        StoredProcedureQuery query = entityManager.createStoredProcedureQuery("seleccionar_producto")
                .registerStoredProcedureParameter("p_id_producto", Long.class, ParameterMode.IN)
                .registerStoredProcedureParameter("p_id_tipo_producto", Long.class, ParameterMode.OUT)
                .registerStoredProcedureParameter("p_nombre", String.class, ParameterMode.OUT)
                .registerStoredProcedureParameter("p_fecha_ingreso", String.class, ParameterMode.OUT)
                .registerStoredProcedureParameter("p_stock", Integer.class, ParameterMode.OUT)
                .registerStoredProcedureParameter("p_detalles", String.class, ParameterMode.OUT)
                .setParameter("p_id_producto", idProducto);

        query.execute();

        Long idTipoProducto = (Long) query.getOutputParameterValue("p_id_tipo_producto");
        String nombre = (String) query.getOutputParameterValue("p_nombre");
        String fechaIngreso = (String) query.getOutputParameterValue("p_fecha_ingreso");
        Integer stock = (Integer) query.getOutputParameterValue("p_stock");
        String detalles = (String) query.getOutputParameterValue("p_detalles");

        Inventario inventario = new Inventario();
        inventario.setIdTipoProducto(idTipoProducto);
        inventario.setNombre(nombre);
        inventario.setFechaIngre(fechaIngreso);
        inventario.setStock(stock);
        inventario.setDetalles(detalles);

        return inventario;
    }

    @Override
    @Transactional
    public void eliminarProducto(Long idProducto) {
        StoredProcedureQuery query = entityManager.createStoredProcedureQuery("eliminar_producto")
                .registerStoredProcedureParameter("p_id_producto", Long.class, ParameterMode.IN)
                .setParameter("p_id_producto", idProducto);

        query.execute();
    }

    @Override
    @Transactional
    public void actualizarProducto(Long idProducto, Long idTipoProducto, String nombre, String fechaIngreso, Integer stock, String detalles) {
        StoredProcedureQuery query = entityManager.createStoredProcedureQuery("actualizar_producto")
                .registerStoredProcedureParameter("p_id_producto", Long.class, ParameterMode.IN)
                .registerStoredProcedureParameter("p_id_tipo_producto", Long.class, ParameterMode.IN)
                .registerStoredProcedureParameter("p_nombre", String.class, ParameterMode.IN)
                .registerStoredProcedureParameter("p_fecha_ingreso", String.class, ParameterMode.IN)
                .registerStoredProcedureParameter("p_stock", Integer.class, ParameterMode.IN)
                .registerStoredProcedureParameter("p_detalles", String.class, ParameterMode.IN)
                .setParameter("p_id_producto", idProducto)
                .setParameter("p_id_tipo_producto", idTipoProducto)
                .setParameter("p_nombre", nombre)
                .setParameter("p_fecha_ingreso", fechaIngreso)
                .setParameter("p_stock", stock)
                .setParameter("p_detalles", detalles);

        query.execute();
    }

    @Override
    @Transactional
    public void insertarProducto(Long idProducto, Long idTipoProducto, String nombre, String fechaIngreso, Integer stock, String detalles) {
        StoredProcedureQuery query = entityManager.createStoredProcedureQuery("insertar_producto")
                .registerStoredProcedureParameter("p_id_producto", Long.class, ParameterMode.IN)
                .registerStoredProcedureParameter("p_id_tipo_producto", Long.class, ParameterMode.IN)
                .registerStoredProcedureParameter("p_nombre", String.class, ParameterMode.IN)
                .registerStoredProcedureParameter("p_fecha_ingreso", String.class, ParameterMode.IN)
                .registerStoredProcedureParameter("p_stock", Integer.class, ParameterMode.IN)
                .registerStoredProcedureParameter("p_detalles", String.class, ParameterMode.IN)
                .setParameter("p_id_producto", idProducto)
                .setParameter("p_id_tipo_producto", idTipoProducto)
                .setParameter("p_nombre", nombre)
                .setParameter("p_fecha_ingreso", fechaIngreso)
                .setParameter("p_stock", stock)
                .setParameter("p_detalles", detalles);

        query.execute();
    }

    @Override
    @Transactional
    public Long obtenerUltimoProducto() {
        StoredProcedureQuery query = entityManager.createStoredProcedureQuery("ObtenerUltimoProducto")
                .registerStoredProcedureParameter("p_id_producto", Long.class, ParameterMode.OUT);

        query.execute();

        return (Long) query.getOutputParameterValue("p_id_producto") + 1;
    }

}
