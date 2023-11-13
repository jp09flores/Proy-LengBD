/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.Proyecto.service.impl;

import com.Proyecto.dao.TrabajosDao;
import com.Proyecto.domain.Trabajos;
import com.Proyecto.service.TrabajosService;
import jakarta.persistence.EntityManager;
import jakarta.persistence.ParameterMode;
import jakarta.persistence.StoredProcedureQuery;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TrabajosServiceImpl implements TrabajosService {

    @Autowired
    TrabajosDao empleadosDao;
    @Autowired
    private EntityManager entityManager;

    @Override
    @Transactional(readOnly = true)
    public List<Trabajos> getTrabajos() {
        List<Trabajos> Trabajos = empleadosDao.findAll();
        return Trabajos;
    }

    @Override
    public Trabajos seleccionarTrabajo(Long idTrabajo) {
        StoredProcedureQuery query = entityManager.createStoredProcedureQuery("seleccionar_trabajo")
                .registerStoredProcedureParameter("p_id_trabajo", Long.class, ParameterMode.IN)
                .registerStoredProcedureParameter("p_id_tipo_trabajo", Long.class, ParameterMode.OUT)
                .registerStoredProcedureParameter("p_fecha", String.class, ParameterMode.OUT)
                .registerStoredProcedureParameter("p_id_cliente", Long.class, ParameterMode.OUT)
                .registerStoredProcedureParameter("p_num_placa", String.class, ParameterMode.OUT)
                .registerStoredProcedureParameter("p_cant_productos", Integer.class, ParameterMode.OUT)
                .registerStoredProcedureParameter("p_id_empleado", Long.class, ParameterMode.OUT)
                .setParameter("p_id_trabajo", idTrabajo);

        query.execute();

        Long idTipoTrabajo = (Long) query.getOutputParameterValue("p_id_tipo_trabajo");
        String fecha = (String) query.getOutputParameterValue("p_fecha");
        Long idCliente = (Long) query.getOutputParameterValue("p_id_cliente");
        String numPlaca = (String) query.getOutputParameterValue("p_num_placa");
        Integer cantProductos = (Integer) query.getOutputParameterValue("p_cant_productos");
        Long idEmpleado = (Long) query.getOutputParameterValue("p_id_empleado");

        Trabajos trabajos = new Trabajos();
        trabajos.setIdTipoTrabajo(idTipoTrabajo);
        trabajos.setFecha(fecha);
        trabajos.setIdCliente(idCliente);
        trabajos.setNumPlaca(numPlaca);
        trabajos.setCantProductos(cantProductos);
        trabajos.setIdEmpleado(idEmpleado);

        return trabajos;
    }

    @Override
    @Transactional
    public void eliminarTrabajo(Long idTrabajo) {
        StoredProcedureQuery query = entityManager.createStoredProcedureQuery("eliminar_trabajo")
                .registerStoredProcedureParameter("p_id_trabajo", Long.class, ParameterMode.IN)
                .setParameter("p_id_trabajo", idTrabajo);

        query.execute();
    }

    @Override
    @Transactional
    public void actualizarTrabajo(Long idTrabajo, Long idTipoTrabajo, String fecha, Long idCliente, String numPlaca, Integer cantProductos, Long idEmpleado) {
        StoredProcedureQuery query = entityManager.createStoredProcedureQuery("actualizar_trabajo")
                .registerStoredProcedureParameter("p_id_trabajo", Long.class, ParameterMode.IN)
                .registerStoredProcedureParameter("p_id_tipo_trabajo", Long.class, ParameterMode.IN)
                .registerStoredProcedureParameter("p_fecha", String.class, ParameterMode.IN)
                .registerStoredProcedureParameter("p_id_cliente", Long.class, ParameterMode.IN)
                .registerStoredProcedureParameter("p_num_placa", String.class, ParameterMode.IN)
                .registerStoredProcedureParameter("p_cant_productos", Integer.class, ParameterMode.IN)
                .registerStoredProcedureParameter("p_id_empleado", Long.class, ParameterMode.IN)
                .setParameter("p_id_trabajo", idTrabajo)
                .setParameter("p_id_tipo_trabajo", idTipoTrabajo)
                .setParameter("p_fecha", fecha)
                .setParameter("p_id_cliente", idCliente)
                .setParameter("p_num_placa", numPlaca)
                .setParameter("p_cant_productos", cantProductos)
                .setParameter("p_id_empleado", idEmpleado);

        query.execute();
    }

    @Override
    @Transactional
    public void insertarTrabajo(Long idTrabajo, Long idTipoTrabajo, String fecha, Long idCliente, String numPlaca, Integer cantProductos, Long idEmpleado) {
        StoredProcedureQuery query = entityManager.createStoredProcedureQuery("insertar_trabajo")
                .registerStoredProcedureParameter("p_id_trabajo", Long.class, ParameterMode.IN)
                .registerStoredProcedureParameter("p_id_tipo_trabajo", Long.class, ParameterMode.IN)
                .registerStoredProcedureParameter("p_fecha", String.class, ParameterMode.IN)
                .registerStoredProcedureParameter("p_id_cliente", Long.class, ParameterMode.IN)
                .registerStoredProcedureParameter("p_num_placa", String.class, ParameterMode.IN)
                .registerStoredProcedureParameter("p_cant_productos", Integer.class, ParameterMode.IN)
                .registerStoredProcedureParameter("p_id_empleado", Long.class, ParameterMode.IN)
                .setParameter("p_id_trabajo", idTrabajo)
                .setParameter("p_id_tipo_trabajo", idTipoTrabajo)
                .setParameter("p_fecha", fecha)
                .setParameter("p_id_cliente", idCliente)
                .setParameter("p_num_placa", numPlaca)
                .setParameter("p_cant_productos", cantProductos)
                .setParameter("p_id_empleado", idEmpleado);

        query.execute();
    }

    @Override
    @Transactional
    public Long obtenerUltimoTrabajo() {
        StoredProcedureQuery query = entityManager.createStoredProcedureQuery("ObtenerUltimoTrabajo")
                .registerStoredProcedureParameter("p_id_trabajo", Long.class, ParameterMode.OUT);

        query.execute();

        return (Long) query.getOutputParameterValue("p_id_trabajo") + 1;
    }
}
