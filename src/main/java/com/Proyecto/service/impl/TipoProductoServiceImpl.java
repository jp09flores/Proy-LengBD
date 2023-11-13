/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.Proyecto.service.impl;

import com.Proyecto.domain.TipoProducto;
import jakarta.persistence.EntityManager;
import jakarta.persistence.ParameterMode;
import jakarta.persistence.StoredProcedureQuery;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.Proyecto.dao.TipoProductoDao;
import com.Proyecto.service.TipoProductoService;

@Service
public class TipoProductoServiceImpl implements TipoProductoService {

    @Autowired
    TipoProductoDao productosDao;

    @Autowired
    private EntityManager entityManager;

    @Override
    public TipoProducto SeleccionarProductos(Long IdTipoProducto) {
        StoredProcedureQuery query = entityManager.createStoredProcedureQuery("seleccionar_tipo_producto")
                .registerStoredProcedureParameter("p_id_tipo_producto", Long.class, ParameterMode.IN)
                .registerStoredProcedureParameter("p_nombre", String.class, ParameterMode.OUT)
                .registerStoredProcedureParameter("p_detalle", String.class, ParameterMode.OUT)
                .setParameter("p_id_tipo_producto", IdTipoProducto);

        query.execute();
        String nombre = (String) query.getOutputParameterValue("p_nombre");
        String detalle = (String) query.getOutputParameterValue("p_detalle");

        TipoProducto producto = new TipoProducto();
        producto.setNombre(nombre);
        producto.setDetalles(detalle);

        return producto;
    }

    @Transactional
    @Override
    public void eliminarTipoProducto(Long IdTipoProducto) {
        StoredProcedureQuery query = entityManager.createStoredProcedureQuery("eliminar_tipo_producto")
                .registerStoredProcedureParameter("p_id_tipo_producto", Long.class, ParameterMode.IN)
                .setParameter("p_id_tipo_producto", IdTipoProducto);

        query.execute();
    }

    @Transactional
    @Override
    public void actualizarProductos(Long IdTipoProducto, String nombre, String detalles) {
        StoredProcedureQuery query = entityManager.createStoredProcedureQuery("actualizar_tipo_producto")
                .registerStoredProcedureParameter("p_id_tipo_producto", Long.class, ParameterMode.IN)
                .registerStoredProcedureParameter("p_nombre", String.class, ParameterMode.IN)
                .registerStoredProcedureParameter("p_detalles", String.class, ParameterMode.IN)
                .setParameter("p_nombre", nombre)
                .setParameter("p_id_tipo_producto", IdTipoProducto)
                .setParameter("p_detalles", detalles);

        query.execute();
    }

    @Transactional
    @Override
    public void insertarProductos(Long IdTipoProducto, String nombre, String detalles) {
        StoredProcedureQuery query = entityManager.createStoredProcedureQuery("insertar_tipo_producto")
                .registerStoredProcedureParameter("p_id_tipo_producto", Long.class, ParameterMode.IN)
                .registerStoredProcedureParameter("p_nombre", String.class, ParameterMode.IN)
                .registerStoredProcedureParameter("p_detalles", String.class, ParameterMode.IN)
                .setParameter("p_id_tipo_producto", IdTipoProducto)
                .setParameter("p_nombre", nombre)
                .setParameter("p_detalles", detalles);

        query.execute();
    }

    @Override
    public Long ObtenerUltimoProducto() {
        StoredProcedureQuery query = entityManager.createStoredProcedureQuery("obtener_ultimo_tipo_producto")
                .registerStoredProcedureParameter("p_id_tipo_producto", Long.class, ParameterMode.OUT);

        query.execute();

        return (Long) query.getOutputParameterValue("p_id_tipo_producto") + 1;
    }

    @Override
    @Transactional(readOnly = true)
    public List<TipoProducto> getProductos() {
        List<TipoProducto> productos = productosDao.findAll();
        return productos;
    }

}
