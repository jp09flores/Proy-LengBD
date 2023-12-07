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
import java.sql.Types;
import java.util.Map;
import javax.sql.DataSource;
import org.springframework.jdbc.core.SqlOutParameter;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;

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

     private final SimpleJdbcCall jdbcCall;

    @Autowired
    public InventarioServiceImpl(DataSource dataSource) {
        this.jdbcCall = new SimpleJdbcCall(dataSource)
                .withFunctionName("F_eliminar_producto")
                .withoutProcedureColumnMetaDataAccess()
                .declareParameters(
                        new SqlOutParameter("RETURN", Types.INTEGER),
                        new SqlParameter("p_id_producto", Types.INTEGER)
                );
    }

    @Override
    @Transactional
    public int eliminarProducto(Long idProducto) {
        MapSqlParameterSource inParams = new MapSqlParameterSource();
        inParams.addValue("p_id_producto", idProducto);

        Map<String, Object> result = jdbcCall.execute(inParams);

        if (result.containsKey("RETURN")) {
            return (int) result.get("RETURN");
        } else {
            return -1;
        }
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
