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
import com.Proyecto.domain.Cursores;
import com.Proyecto.service.TipoProductoService;
import java.sql.Types;
import java.util.Map;
import javax.sql.DataSource;
import org.springframework.jdbc.core.SqlOutParameter;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;

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

     private final SimpleJdbcCall jdbcCall;

    @Autowired
    public TipoProductoServiceImpl(DataSource dataSource) {
        this.jdbcCall = new SimpleJdbcCall(dataSource)
                .withCatalogName("PKG_TIPO_PRODUCTO")
                .withFunctionName("F_eliminar_tipo_producto")
                .withoutProcedureColumnMetaDataAccess()
                .declareParameters(
                        new SqlOutParameter("RETURN", Types.INTEGER),
                        new SqlParameter("p_id", Types.INTEGER)
                );
    }

    @Override
    @Transactional
    public int eliminarTipoProducto(Long idTipoProducto) {
        MapSqlParameterSource inParams = new MapSqlParameterSource();
        inParams.addValue("p_id", idTipoProducto);

        Map<String, Object> result = jdbcCall.execute(inParams);

        if (result.containsKey("RETURN")) {
            return (int) result.get("RETURN");
        } else {
            return -1;
        }
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
    
    
    @Override
    public Cursores Cursor() {
        StoredProcedureQuery query = entityManager.createStoredProcedureQuery("c_RECUPERAR_TIPOS_PRODUCTO")
                .registerStoredProcedureParameter("p_id_tipo_producto", Long.class, ParameterMode.OUT)
                .registerStoredProcedureParameter("p_nombre", String.class, ParameterMode.OUT)
                .registerStoredProcedureParameter("p_detalle", String.class, ParameterMode.OUT)
                .registerStoredProcedureParameter("p_output", String.class, ParameterMode.OUT);
        query.execute();
       String output = (String) query.getOutputParameterValue("p_output");

        Cursores cursores = new Cursores();
        cursores.setOutput(output);

        return cursores;
    }
}
