package com.Proyecto.service.impl;

import com.Proyecto.dao.VehiculoDao;
import com.Proyecto.domain.Vehiculo;
import com.Proyecto.service.VehiculoService;
import jakarta.persistence.EntityManager;
import jakarta.persistence.ParameterMode;
import jakarta.persistence.StoredProcedureQuery;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


public class VehiculoServiceImpl implements VehiculoService {

    @Autowired
    VehiculoDao vehiculoDao;
    
    @Autowired
    private EntityManager entityManager;
    
    
    
    @Override
    @Transactional(readOnly = true)
    public List<Vehiculo> getTiposVehiculos() {
        List<Vehiculo> Vehiculo = vehiculoDao.findAll();
        return Vehiculo;
    }

    @Override
    public Vehiculo seleccionarVehiculo(String numPlaca) {
        StoredProcedureQuery query = entityManager.createStoredProcedureQuery("seleccionar_vehiculo")
                .registerStoredProcedureParameter("v_num", String.class, ParameterMode.IN)
                .registerStoredProcedureParameter("v_num_motor", String.class, ParameterMode.OUT)
                .registerStoredProcedureParameter("v_marca", String.class, ParameterMode.OUT)
                .registerStoredProcedureParameter("v_color", String.class, ParameterMode.OUT)
                .registerStoredProcedureParameter("v_modelo", String.class, ParameterMode.OUT)
                .registerStoredProcedureParameter("v_year", String.class, ParameterMode.OUT)
                .setParameter("v_num", numPlaca);
        
        query.execute();
        
        String numMotor = (String) query.getOutputParameterValue("v_num_motor");
        String marca = (String) query.getOutputParameterValue("v_marca");
        String color = (String) query.getOutputParameterValue("v_color");
        String modelo = (String) query.getOutputParameterValue("v_modelo");
        String year = (String) query.getOutputParameterValue("v_year");
        
        Vehiculo vehiculo = new Vehiculo();
        vehiculo.setNumMotor(numMotor);
        vehiculo.setMarca(marca);
        vehiculo.setColor(color);
        vehiculo.setModelo(modelo);
        vehiculo.setYear(year);
        return vehiculo;
    }

    @Override
    @Transactional
    public void actualizarVehiculo(String numPlaca, String numMotor, String marca, String color, String modelo, String year) {
        entityManager.createStoredProcedureQuery("actualizar_vehiculo")
                .registerStoredProcedureParameter("v_num", String.class, ParameterMode.IN)
                .registerStoredProcedureParameter("v_marca", String.class, ParameterMode.IN)
                .registerStoredProcedureParameter("v_color", String.class, ParameterMode.IN)
                .registerStoredProcedureParameter("v_modelo", String.class, ParameterMode.IN)
                .registerStoredProcedureParameter("v_año", String.class, ParameterMode.IN)
                .setParameter("v_num", numPlaca)
                .setParameter("v_marca", marca)
                .setParameter("v_color", color)
                .setParameter("v_modelo", modelo)
                .setParameter("v_year", year)
                .execute();
    }

    @Override
    public void eliminarVehiculo(String numPlaca) {
        entityManager.createStoredProcedureQuery("eliminar_vehiculo")
                .registerStoredProcedureParameter("v_num", String.class, ParameterMode.IN)
                .setParameter("v_num", numPlaca)
                .execute();
    }

    @Override
    public void insertarVehiculo(String numPlaca, String numMotor, String marca, String color, String modelo, String year) {
        entityManager.createStoredProcedureQuery("insertar_vehiculo")
                .registerStoredProcedureParameter("v_numPlaca", String.class, ParameterMode.IN)
                .registerStoredProcedureParameter("v_num_motor", String.class, ParameterMode.IN)
                .registerStoredProcedureParameter("v_marca", String.class, ParameterMode.IN)
                .registerStoredProcedureParameter("v_color", String.class, ParameterMode.IN)
                .registerStoredProcedureParameter("v_modelo", String.class, ParameterMode.IN)
                .registerStoredProcedureParameter("v_year", String.class, ParameterMode.IN)
                
                .setParameter("v_numPlaca", numPlaca)
                .setParameter("v_num_motor", numMotor)
                .setParameter("v_marca", marca)
                .setParameter("v_color", color)
                .setParameter("v_modelo", modelo)
                .setParameter("v_year", year)
                .execute();
    }    
}