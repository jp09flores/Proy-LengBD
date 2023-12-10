/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.Proyecto.service.impl;

/**
 *
 * @author jp09f
 */


import com.Proyecto.dao.ValoracionDao;
import com.Proyecto.domain.Cursores;
import com.Proyecto.domain.Valoracion;
import com.Proyecto.service.ValoracionService;
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


@Service
public class ValoracionServiceImpl  implements ValoracionService{
    @Autowired
    ValoracionDao valoracionDao;
    @Autowired
    private EntityManager entityManager;
    
    @Override
    @Transactional(readOnly = true)
    public List<Valoracion> getValoraciones() {
         List<Valoracion> valoracion = valoracionDao.findAll();
        return valoracion;
    }
    
     
    
    @Override
    public Valoracion seleccionarValoracion(Long idValoracion) {
        StoredProcedureQuery query = entityManager.createStoredProcedureQuery("seleccionar_valoracion")
                .registerStoredProcedureParameter("p_id_valoracion", Long.class, ParameterMode.IN)
                .registerStoredProcedureParameter("p_id_cliente", Long.class, ParameterMode.OUT)
                .registerStoredProcedureParameter("p_comentario", String.class, ParameterMode.OUT)
                .registerStoredProcedureParameter("p_valoracion", Integer.class, ParameterMode.OUT)
                .registerStoredProcedureParameter("p_fecha_emi", String.class, ParameterMode.OUT)
                .setParameter("p_id_valoracion", idValoracion);

        query.execute();

        Long idCliente = (Long) query.getOutputParameterValue("p_id_cliente");
        String comentario = (String) query.getOutputParameterValue("p_comentario");
        Integer valoracion = (Integer) query.getOutputParameterValue("p_valoracion");
        String fechaEmi = (String) query.getOutputParameterValue("p_fecha_emi");

        Valoracion v = new Valoracion();
        v.setIdCliente(idCliente);
        v.setComentario(comentario);
        v.setValoracion(valoracion);
        v.setFechaEmi(fechaEmi);

        return v;
    }
    private final SimpleJdbcCall jdbcCall;

    @Autowired
    public ValoracionServiceImpl(DataSource dataSource) {
        this.jdbcCall = new SimpleJdbcCall(dataSource)
                .withCatalogName("PKG_VALORACIONES")
                .withFunctionName("F_eliminar_valoracion")
                .withoutProcedureColumnMetaDataAccess()
                .declareParameters(
                        new SqlOutParameter("RETURN", Types.INTEGER),
                        new SqlParameter("p_id_valoracion", Types.INTEGER)
                );
    }

    @Override
    @Transactional
    public int eliminarValoracion(Long idValoracion) {
        MapSqlParameterSource inParams = new MapSqlParameterSource();
        inParams.addValue("p_id_valoracion", idValoracion);

        Map<String, Object> result = jdbcCall.execute(inParams);

        if (result.containsKey("RETURN")) {
            return (int) result.get("RETURN");
        } else {
            return -1;
        }
    }
    @Override
    @Transactional
    public void actualizarValoracion(Long idValoracion, Long idCliente, String comentario, Integer valoracion, String fechaEmi) {
        StoredProcedureQuery query = entityManager.createStoredProcedureQuery("actualizar_valoracion")
                .registerStoredProcedureParameter("p_id_valoracion", Long.class, ParameterMode.IN)
                .registerStoredProcedureParameter("p_id_cliente", Long.class, ParameterMode.IN)
                .registerStoredProcedureParameter("p_comentario", String.class, ParameterMode.IN)
                .registerStoredProcedureParameter("p_valoracion", Integer.class, ParameterMode.IN)
                .registerStoredProcedureParameter("p_fecha_emi", String.class, ParameterMode.IN)
                .setParameter("p_id_valoracion", idValoracion)
                .setParameter("p_id_cliente", idCliente)
                .setParameter("p_comentario", comentario)
                .setParameter("p_valoracion", valoracion)
                .setParameter("p_fecha_emi", fechaEmi);

        query.execute();
    }
    @Override
    @Transactional
    public void insertarValoracion(Long idValoracion, Long idCliente, String comentario, Integer valoracion, String fechaEmi) {
        StoredProcedureQuery query = entityManager.createStoredProcedureQuery("insertar_valoracion")
                .registerStoredProcedureParameter("p_id_valoracion", Long.class, ParameterMode.IN)
                .registerStoredProcedureParameter("p_id_cliente", Long.class, ParameterMode.IN)
                .registerStoredProcedureParameter("p_comentario", String.class, ParameterMode.IN)
                .registerStoredProcedureParameter("p_valoracion", Integer.class, ParameterMode.IN)
                .registerStoredProcedureParameter("p_fecha_emi", String.class, ParameterMode.IN)
                .setParameter("p_id_valoracion", idValoracion)
                .setParameter("p_id_cliente", idCliente)
                .setParameter("p_comentario", comentario)
                .setParameter("p_valoracion", valoracion)
                .setParameter("p_fecha_emi", fechaEmi);

        query.execute();
    }
    @Override
    public Long obtenerUltimaValoracion() {
        StoredProcedureQuery query = entityManager.createStoredProcedureQuery("ObtenerUltimaValoracion")
                .registerStoredProcedureParameter("p_id_valoracion", Long.class, ParameterMode.OUT);

        query.execute();

        Long idUltimaValoracion = (Long) query.getOutputParameterValue("p_id_valoracion");

        return idUltimaValoracion+1;
    }
 @Override
    public Cursores Cursor() {
        StoredProcedureQuery query = entityManager.createStoredProcedureQuery("BUSCAR_VALORACIONES_MAYOR_A_3")
                .registerStoredProcedureParameter("v_id_cliente", int.class, ParameterMode.OUT)
                .registerStoredProcedureParameter("v_comentario", String.class, ParameterMode.OUT)
                .registerStoredProcedureParameter("v_valoracion", int.class, ParameterMode.OUT)
                .registerStoredProcedureParameter("v_fecha_emision", String.class, ParameterMode.OUT)
                .registerStoredProcedureParameter("v_output", String.class, ParameterMode.OUT);
        query.execute();
       String output = (String) query.getOutputParameterValue("v_output");

        Cursores cursores = new Cursores();
        cursores.setOutput(output);

        return cursores;
    }
}
