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

@Service
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
    @Transactional
    public Vehiculo seleccionarVehiculo(String numPlaca) {
        StoredProcedureQuery query = entityManager.createStoredProcedureQuery("seleccionar_vehiculo")
                .registerStoredProcedureParameter("p_num_placa", String.class, ParameterMode.IN)
                .registerStoredProcedureParameter("p_num_motor", String.class, ParameterMode.OUT)
                .registerStoredProcedureParameter("p_marca", String.class, ParameterMode.OUT)
                .registerStoredProcedureParameter("p_color", String.class, ParameterMode.OUT)
                .registerStoredProcedureParameter("p_modelo", String.class, ParameterMode.OUT)
                .registerStoredProcedureParameter("p_year", String.class, ParameterMode.OUT)
                .setParameter("p_num_placa", numPlaca);

        query.execute();

        String numMotor = (String) query.getOutputParameterValue("p_num_motor");
        String marca = (String) query.getOutputParameterValue("p_marca");
        String color = (String) query.getOutputParameterValue("p_color");
        String modelo = (String) query.getOutputParameterValue("p_modelo");
        String year = (String) query.getOutputParameterValue("p_year");

        Vehiculo vehiculo = new Vehiculo();
        vehiculo.setNumMotor(numMotor);
        vehiculo.setMarca(marca);
        vehiculo.setColor(color);
        vehiculo.setNumPlaca(numPlaca);
        vehiculo.setModelo(modelo);
        vehiculo.setAno(year);
        
        return vehiculo;
    }

    @Override
    @Transactional
    public void eliminarVehiculo(String numPlaca) {
        StoredProcedureQuery query = entityManager.createStoredProcedureQuery("eliminar_vehiculo")
                .registerStoredProcedureParameter("p_num_placa", String.class, ParameterMode.IN)
                .setParameter("p_num_placa", numPlaca);

        query.execute();
    }

    @Override
    @Transactional
    public void actualizarVehiculo(String numPlaca, String numMotor, String marca, String color, String modelo, String year) {
        StoredProcedureQuery query = entityManager.createStoredProcedureQuery("actualizar_vehiculo")
                .registerStoredProcedureParameter("p_num_placa", String.class, ParameterMode.IN)
                .registerStoredProcedureParameter("p_num_motor", String.class, ParameterMode.IN)
                .registerStoredProcedureParameter("p_marca", String.class, ParameterMode.IN)
                .registerStoredProcedureParameter("p_color", String.class, ParameterMode.IN)
                .registerStoredProcedureParameter("p_modelo", String.class, ParameterMode.IN)
                .registerStoredProcedureParameter("p_year", String.class, ParameterMode.IN)
                .setParameter("p_num_placa", numPlaca)
                .setParameter("p_num_motor", numMotor)
                .setParameter("p_marca", marca)
                .setParameter("p_color", color)
                .setParameter("p_modelo", modelo)
                .setParameter("p_year", year);

        query.execute();
    }

    @Override
    @Transactional
    public void insertarVehiculo(String numPlaca, String numMotor, String marca, String color, String modelo, String year) {
        StoredProcedureQuery query = entityManager.createStoredProcedureQuery("insertar_vehiculo")
                .registerStoredProcedureParameter("p_num_placa", String.class, ParameterMode.IN)
                .registerStoredProcedureParameter("p_num_motor", String.class, ParameterMode.IN)
                .registerStoredProcedureParameter("p_marca", String.class, ParameterMode.IN)
                .registerStoredProcedureParameter("p_color", String.class, ParameterMode.IN)
                .registerStoredProcedureParameter("p_modelo", String.class, ParameterMode.IN)
                .registerStoredProcedureParameter("p_year", String.class, ParameterMode.IN)
                .setParameter("p_num_placa", numPlaca)
                .setParameter("p_num_motor", numMotor)
                .setParameter("p_marca", marca)
                .setParameter("p_color", color)
                .setParameter("p_modelo", modelo)
                .setParameter("p_year", year);

        query.execute();
    }
}
