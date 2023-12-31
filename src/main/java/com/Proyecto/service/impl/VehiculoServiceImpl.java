package com.Proyecto.service.impl;

import com.Proyecto.dao.VehiculoDao;
import com.Proyecto.domain.Cursores;
import com.Proyecto.domain.Vehiculo;
import com.Proyecto.service.VehiculoService;
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
                .registerStoredProcedureParameter("p_id_cliente", Long.class, ParameterMode.OUT)
                .registerStoredProcedureParameter("p_num_motor", String.class, ParameterMode.OUT)
                .registerStoredProcedureParameter("p_marca", String.class, ParameterMode.OUT)
                .registerStoredProcedureParameter("p_color", String.class, ParameterMode.OUT)
                .registerStoredProcedureParameter("p_modelo", String.class, ParameterMode.OUT)
                .registerStoredProcedureParameter("p_ano", String.class, ParameterMode.OUT)
                .setParameter("p_num_placa", numPlaca);

        query.execute();

        Long idCliente = (Long) query.getOutputParameterValue("p_id_cliente");
        String numMotor = (String) query.getOutputParameterValue("p_num_motor");
        String marca = (String) query.getOutputParameterValue("p_marca");
        String color = (String) query.getOutputParameterValue("p_color");
        String modelo = (String) query.getOutputParameterValue("p_modelo");
        String ano = (String) query.getOutputParameterValue("p_ano");

        Vehiculo vehiculo = new Vehiculo();
        vehiculo.setIdCliente(idCliente);
        vehiculo.setNumMotor(numMotor);
        vehiculo.setMarca(marca);
        vehiculo.setColor(color);
        vehiculo.setModelo(modelo);
        vehiculo.setAno(ano);
        vehiculo.setNumPlaca(numPlaca);

        return vehiculo;
    }

    private final SimpleJdbcCall jdbcCall;

    @Autowired
    public VehiculoServiceImpl(DataSource dataSource) {
        this.jdbcCall = new SimpleJdbcCall(dataSource)
                .withFunctionName("F_eliminar_vehiculo")
                .withoutProcedureColumnMetaDataAccess()
                .declareParameters(
                        new SqlOutParameter("RETURN", Types.INTEGER),
                        new SqlParameter("p_num_placa", Types.VARCHAR)
                );
    }

    @Override
    @Transactional
    public int eliminarVehiculo(String numPlaca) {
        MapSqlParameterSource inParams = new MapSqlParameterSource();
        inParams.addValue("p_num_placa", numPlaca);

        Map<String, Object> result = jdbcCall.execute(inParams);

        if (result.containsKey("RETURN")) {
            return (int) result.get("RETURN");
        } else {
            return -1;
        }
    }

    @Override
    @Transactional
    public void actualizarVehiculo(String numPlaca, String numMotor, Long idCliente, String marca, String color, String modelo, String year) {
         StoredProcedureQuery query = entityManager.createStoredProcedureQuery("actualizar_vehiculo")
                .registerStoredProcedureParameter("p_num_placa", String.class, ParameterMode.IN)
                .registerStoredProcedureParameter("p_id_cliente", Long.class, ParameterMode.IN)
                .registerStoredProcedureParameter("p_num_motor", Long.class, ParameterMode.IN)
                .registerStoredProcedureParameter("p_marca", String.class, ParameterMode.IN)
                .registerStoredProcedureParameter("p_color", String.class, ParameterMode.IN)
                .registerStoredProcedureParameter("p_modelo", String.class, ParameterMode.IN)
                .registerStoredProcedureParameter("p_ano", String.class, ParameterMode.IN)
                .setParameter("p_num_placa", numPlaca)
                .setParameter("p_id_cliente", idCliente)
                .setParameter("p_num_motor", numMotor)
                .setParameter("p_marca", marca)
                .setParameter("p_color", color)
                .setParameter("p_modelo", modelo)
                .setParameter("p_ano", year);
         query.execute();
    }

    @Override
    @Transactional
    public void insertarVehiculo(String numPlaca, String numMotor, Long idCliente, String marca, String color, String modelo, String year) {
        StoredProcedureQuery query = entityManager.createStoredProcedureQuery("insertar_vehiculo")
                .registerStoredProcedureParameter("p_num_placa", String.class, ParameterMode.IN)
                .registerStoredProcedureParameter("p_id_cliente", Long.class, ParameterMode.IN)
                .registerStoredProcedureParameter("p_num_motor", String.class, ParameterMode.IN)
                .registerStoredProcedureParameter("p_marca", String.class, ParameterMode.IN)
                .registerStoredProcedureParameter("p_color", String.class, ParameterMode.IN)
                .registerStoredProcedureParameter("p_modelo", String.class, ParameterMode.IN)
                .registerStoredProcedureParameter("p_ano", String.class, ParameterMode.IN)
                .setParameter("p_num_placa", numPlaca)
                .setParameter("p_id_cliente", idCliente)
                .setParameter("p_num_motor", numMotor)
                .setParameter("p_marca", marca)
                .setParameter("p_color", color)
                .setParameter("p_modelo", modelo)
                .setParameter("p_ano", year);

        query.execute();
    }

    @Override
    public Cursores Cursor(char placa) {
        StoredProcedureQuery query = entityManager.createStoredProcedureQuery("BUSCAR_VEHICULOS_POR_PLACA")
                .registerStoredProcedureParameter("p_inicio_placa", char.class, ParameterMode.IN)
                .registerStoredProcedureParameter("p_num_placa", String.class, ParameterMode.OUT)
                .registerStoredProcedureParameter("p_num_motor", String.class, ParameterMode.OUT)
                .registerStoredProcedureParameter("p_marca", String.class, ParameterMode.OUT)
                .registerStoredProcedureParameter("p_color", String.class, ParameterMode.OUT)
                .registerStoredProcedureParameter("p_modelo", String.class, ParameterMode.OUT)
                .registerStoredProcedureParameter("p_ano", String.class, ParameterMode.OUT)
                .registerStoredProcedureParameter("p_output", String.class, ParameterMode.OUT)
                .setParameter("p_inicio_placa", placa);
        query.execute();
        String output = (String) query.getOutputParameterValue("p_output");

        Cursores cursores = new Cursores();
        cursores.setOutput(output);

        return cursores;
    }

    
}
