package com.Proyecto.service.impl;

import com.Proyecto.dao.TipoTrabajoDao;
import com.Proyecto.domain.TipoTrabajo;
import com.Proyecto.service.TipoTrabajoService;
import jakarta.persistence.EntityManager;
import jakarta.persistence.ParameterMode;
import jakarta.persistence.StoredProcedureQuery;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TipoTrabajoServiceImpl implements TipoTrabajoService{

    @Autowired
    TipoTrabajoDao tipoTrabajoDao;
    
    @Autowired
    private EntityManager entityManager;
    
    
    
    @Override
    @Transactional(readOnly = true)
    public List<TipoTrabajo> getTiposTrabajos() {
        List<TipoTrabajo> TipoTrabajo = tipoTrabajoDao.findAll();
        return TipoTrabajo;    }

    @Override
    public TipoTrabajo seleccionarTipoTrabajo(Long idTipoTrabajo) {
        StoredProcedureQuery query = entityManager.createStoredProcedureQuery("seleccionar_cliente")
                .registerStoredProcedureParameter("t_id", Long.class, ParameterMode.IN)
                .registerStoredProcedureParameter("t_nombre", String.class, ParameterMode.OUT)
                .registerStoredProcedureParameter("t_requisitos", String.class, ParameterMode.OUT)
                .registerStoredProcedureParameter("t_contenido", String.class, ParameterMode.OUT)
                .registerStoredProcedureParameter("t_detalles", String.class, ParameterMode.OUT)
                .setParameter("t_id", idTipoTrabajo);
        
        query.execute();

        String nombre = (String) query.getOutputParameterValue("t_nombre");
        String requisitos = (String) query.getOutputParameterValue("t_requisitos");
        String contenido = (String) query.getOutputParameterValue("t_contenido");
        String detalles = (String) query.getOutputParameterValue("t_detalles");

        TipoTrabajo tipoTrabajo = new TipoTrabajo();
        tipoTrabajo.setNombre(nombre);
        tipoTrabajo.setRequisitos(requisitos);
        tipoTrabajo.setContenido(contenido);
        tipoTrabajo.setDetalles(detalles);
        return tipoTrabajo;
    }

    @Override
    @Transactional
    public void actualizarTipoTrabajo(Long idTipoTrabajo, String nombre, String requisitos) {
       entityManager.createStoredProcedureQuery("actualizar_tipo_trabajo")
                .registerStoredProcedureParameter("t_id", Long.class, ParameterMode.IN)
                .registerStoredProcedureParameter("t_nombre", String.class, ParameterMode.IN)
                .registerStoredProcedureParameter("t_requisitos", String.class, ParameterMode.IN)
                .setParameter("t_id", idTipoTrabajo)
                .setParameter("t_nombre", nombre)
                .setParameter("t_requisitos", requisitos)
                .execute();
    }

    @Override
    @Transactional
    public void eliminarTipoTrabajo(Long idTipoTrabajo) {
        entityManager.createStoredProcedureQuery("eliminar_tipo_trabajo")
                .registerStoredProcedureParameter("t_id", Long.class, ParameterMode.IN)
                .setParameter("t_id", idTipoTrabajo)
                .execute();
    }

    @Override
    public Long obtenerUltimoTipoTrabajo() {
        StoredProcedureQuery query = entityManager.createStoredProcedureQuery("ObtenerUltimoTipoTrabajo")
                .registerStoredProcedureParameter("t_id", Long.class, ParameterMode.OUT);

        query.execute();

        Long ultimoID = (Long) query.getOutputParameterValue("t_id");

        return ultimoID + 1;    
    }    

    @Override
    public void insertarTipoTrabajo(Long idTipoTrabajo, String nombre, String requisitos, String contenido, String detalles) {
        entityManager.createStoredProcedureQuery("insertar_tipo_trabajo")
                .registerStoredProcedureParameter("t_id", Long.class, ParameterMode.IN)
                .registerStoredProcedureParameter("t_nombre", String.class, ParameterMode.IN)
                .registerStoredProcedureParameter("t_requisitos", String.class, ParameterMode.IN)
                .registerStoredProcedureParameter("t_contenido", String.class, ParameterMode.IN)
                .registerStoredProcedureParameter("t_detalles", String.class, ParameterMode.IN)
               
                .setParameter("t_id", idTipoTrabajo)
                .setParameter("t_nombre", nombre)
                .setParameter("t_requisitos", requisitos)
                .setParameter("t_contenido", contenido)
                .setParameter("t_detalles", detalles)
                .execute();
    }    
}
