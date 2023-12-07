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

import java.sql.Types;
import java.util.Map;
import javax.sql.DataSource;
import org.springframework.jdbc.core.SqlOutParameter;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;

@Service
public class TipoTrabajoServiceImpl implements TipoTrabajoService {

    @Autowired
    TipoTrabajoDao tipoTrabajoDao;

    @Autowired
    private EntityManager entityManager;

    @Override
    @Transactional(readOnly = true)
    public List<TipoTrabajo> getTiposTrabajos() {
        List<TipoTrabajo> TipoTrabajo = tipoTrabajoDao.findAll();
        return TipoTrabajo;
    }

    @Override
    @Transactional
    public TipoTrabajo seleccionarTipoTrabajo(Long idTipoTrabajo) {
        StoredProcedureQuery query = entityManager.createStoredProcedureQuery("seleccionar_tipo_trabajo")
                .registerStoredProcedureParameter("p_id_tipo_trabajo", Long.class, ParameterMode.IN)
                .registerStoredProcedureParameter("p_nombre", String.class, ParameterMode.OUT)
                .registerStoredProcedureParameter("p_requisitos", String.class, ParameterMode.OUT)
                .registerStoredProcedureParameter("p_contenido", String.class, ParameterMode.OUT)
                .registerStoredProcedureParameter("p_detalles", String.class, ParameterMode.OUT)
                .setParameter("p_id_tipo_trabajo", idTipoTrabajo);

        query.execute();

        String nombre = (String) query.getOutputParameterValue("p_nombre");
        String requisitos = (String) query.getOutputParameterValue("p_requisitos");
        String contenido = (String) query.getOutputParameterValue("p_contenido");
        String detalles = (String) query.getOutputParameterValue("p_detalles");

        return new TipoTrabajo(idTipoTrabajo, nombre, requisitos, contenido, detalles);
    }

    @Override
    @Transactional
    public void eliminarTipoTrabajo(Long idTipoTrabajo) {
        StoredProcedureQuery query = entityManager.createStoredProcedureQuery("eliminar_tipo_trabajo")
                .registerStoredProcedureParameter("p_id_tipo_trabajo", Long.class, ParameterMode.IN)
                .setParameter("p_id_tipo_trabajo", idTipoTrabajo);

        query.execute();
    }
     private final SimpleJdbcCall jdbcCall;

    @Autowired
    public TipoTrabajoServiceImpl(DataSource dataSource) {
        this.jdbcCall = new SimpleJdbcCall(dataSource)
                .withFunctionName("obtener_lastjob")
                .withoutProcedureColumnMetaDataAccess()
                .declareParameters(
                        new SqlOutParameter("RETURN", Types.VARCHAR)
                );
    }

    @Transactional
    public String obtenerUltimoTrabajo() {
        Map<String, Object> result = jdbcCall.execute(new MapSqlParameterSource());

        if (result.containsKey("RETURN")) {
            return (String) result.get("RETURN");
        } else {
            return "Error al obtener el último trabajo";
        }
    }

    @Override
    @Transactional
    public void actualizarTipoTrabajo(Long idTipoTrabajo, String nombre, String requisitos, String contenido, String detalles) {
        StoredProcedureQuery query = entityManager.createStoredProcedureQuery("actualizar_tipo_trabajo")
                .registerStoredProcedureParameter("p_id_tipo_trabajo", Long.class, ParameterMode.IN)
                .registerStoredProcedureParameter("p_nombre", String.class, ParameterMode.IN)
                .registerStoredProcedureParameter("p_requisitos", String.class, ParameterMode.IN)
                .registerStoredProcedureParameter("p_contenido", String.class, ParameterMode.IN)
                .registerStoredProcedureParameter("p_detalles", String.class, ParameterMode.IN)
                .setParameter("p_id_tipo_trabajo", idTipoTrabajo)
                .setParameter("p_nombre", nombre)
                .setParameter("p_requisitos", requisitos)
                .setParameter("p_contenido", contenido)
                .setParameter("p_detalles", detalles);

        query.execute();
    }

    @Override
    @Transactional
    public void insertarTipoTrabajo(Long idTipoTrabajo, String nombre, String requisitos, String contenido, String detalles) {
        StoredProcedureQuery query = entityManager.createStoredProcedureQuery("insertar_tipo_trabajo")
                .registerStoredProcedureParameter("p_id_tipo_trabajo", Long.class, ParameterMode.IN)
                .registerStoredProcedureParameter("p_nombre", String.class, ParameterMode.IN)
                .registerStoredProcedureParameter("p_requisitos", String.class, ParameterMode.IN)
                .registerStoredProcedureParameter("p_contenido", String.class, ParameterMode.IN)
                .registerStoredProcedureParameter("p_detalles", String.class, ParameterMode.IN)
                .setParameter("p_id_tipo_trabajo", idTipoTrabajo)
                .setParameter("p_nombre", nombre)
                .setParameter("p_requisitos", requisitos)
                .setParameter("p_contenido", contenido)
                .setParameter("p_detalles", detalles);

        query.execute();
    }
    
    @Override
    @Transactional
    public Long obtenerUltimoTipoTrabajo() {
        StoredProcedureQuery query = entityManager.createStoredProcedureQuery("ObtenerUltimoTipoTrabajo")
                .registerStoredProcedureParameter("p_id_tipo_trabajo", Long.class, ParameterMode.OUT);

        query.execute();

        return (Long) query.getOutputParameterValue("p_id_tipo_trabajo")+1;
    }
}
