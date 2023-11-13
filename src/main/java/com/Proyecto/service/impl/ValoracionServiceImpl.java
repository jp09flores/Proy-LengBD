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
import com.Proyecto.domain.Valoracion;
import com.Proyecto.service.ValoracionService;
import jakarta.persistence.EntityManager;
import jakarta.persistence.ParameterMode;
import jakarta.persistence.StoredProcedureQuery;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    @Override
    @Transactional
    public void eliminarValoracion(Long idValoracion) {
        StoredProcedureQuery query = entityManager.createStoredProcedureQuery("eliminar_valoracion")
                .registerStoredProcedureParameter("p_id_valoracion", Long.class, ParameterMode.IN)
                .setParameter("p_id_valoracion", idValoracion);

        query.execute();
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

}
