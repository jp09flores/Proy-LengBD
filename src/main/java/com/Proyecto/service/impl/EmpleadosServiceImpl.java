/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.Proyecto.service.impl;

import com.Proyecto.service.EmpleadosService;
import com.Proyecto.dao.EmpleadosDao;
import com.Proyecto.domain.Empleados;
import jakarta.persistence.EntityManager;
import jakarta.persistence.ParameterMode;
import jakarta.persistence.StoredProcedureQuery;
import java.math.BigDecimal;
import java.util.Date;
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
public class EmpleadosServiceImpl implements EmpleadosService {
    
    @Autowired
    EmpleadosDao empleadosDao;
    @Override
    @Transactional(readOnly = true)
    public List<Empleados> getEmpleados() {
        List<Empleados> Empleados = empleadosDao.findAll();
        return Empleados;
    }
 
@Autowired
private EntityManager entityManager;

@Override
public Empleados SeleccionarEmpleado(Long idEmpleado) {
    StoredProcedureQuery query = entityManager.createStoredProcedureQuery("seleccionar_empleado")
            .registerStoredProcedureParameter("p_id_empleado", Long.class, ParameterMode.IN)
            .registerStoredProcedureParameter("p_nombre", String.class, ParameterMode.OUT)
            .registerStoredProcedureParameter("p_apellido", String.class, ParameterMode.OUT)
            .registerStoredProcedureParameter("p_direccion", String.class, ParameterMode.OUT)
            .registerStoredProcedureParameter("p_num_telefono", String.class, ParameterMode.OUT)
            .registerStoredProcedureParameter("p_correo", String.class, ParameterMode.OUT)
            .registerStoredProcedureParameter("p_salario", String.class, ParameterMode.OUT)
            .registerStoredProcedureParameter("p_fecha_ingreso", String.class, ParameterMode.OUT)
            .registerStoredProcedureParameter("p_fecha_nacimiento", String.class, ParameterMode.OUT)
            .setParameter("p_id_empleado", idEmpleado);

    query.execute();

    String nombre = (String) query.getOutputParameterValue("p_nombre");
    String apellido = (String) query.getOutputParameterValue("p_apellido");
    String direccion = (String) query.getOutputParameterValue("p_direccion");
    String numTelefono = (String) query.getOutputParameterValue("p_num_telefono");
    String correoElect = (String) query.getOutputParameterValue("p_correo");
    String salario = (String) query.getOutputParameterValue("p_salario");
    String FechaIng = (String) query.getOutputParameterValue("p_fecha_ingreso");
    String FechaNac = (String) query.getOutputParameterValue("p_fecha_nacimiento");

    Empleados empleados = new Empleados();
    empleados.setNombre(nombre);
    empleados.setApellido(apellido);
    empleados.setDireccion(direccion);
    empleados.setNumTelefono(numTelefono);
    empleados.setCorreoElect(correoElect);
    empleados.setSalario(salario);
    empleados.setFechaIngre(FechaIng);
    empleados.setFechaNac(FechaNac);
    return empleados;
}

 private final SimpleJdbcCall jdbcCall;

    @Autowired
    public EmpleadosServiceImpl(DataSource dataSource) {
        this.jdbcCall = new SimpleJdbcCall(dataSource)
                .withFunctionName("F_eliminar_empleado")
                .withoutProcedureColumnMetaDataAccess()
                .declareParameters(
                        new SqlOutParameter("RETURN", Types.INTEGER),
                        new SqlParameter("p_id_empleado", Types.INTEGER)
                );
    }

    @Override
    @Transactional
    public int eliminarEmpleado(Long idEmpleado) {
        MapSqlParameterSource inParams = new MapSqlParameterSource();
        inParams.addValue("p_id_empleado", idEmpleado);

        Map<String, Object> result = jdbcCall.execute(inParams);

        if (result.containsKey("RETURN")) {
            return (int) result.get("RETURN");
        } else {
            return -1;
        }
    }

@Transactional
@Override
 public void actualizarEmpleado(Long IdEmpleado, String nombre,
            String apellido,
            String direccion,
            String numTelefono,
            String correoElect,
            String salario,
            String FechaIngre,
            String FechaNac) {
    StoredProcedureQuery query = entityManager.createStoredProcedureQuery("actualizar_empleado")
            .registerStoredProcedureParameter("p_id_empleado", Long.class, ParameterMode.IN)
            .registerStoredProcedureParameter("p_nombre", String.class, ParameterMode.IN)
            .registerStoredProcedureParameter("p_apellido", String.class, ParameterMode.IN)
            .registerStoredProcedureParameter("p_direccion", String.class, ParameterMode.IN)
            .registerStoredProcedureParameter("p_num_telefono", String.class, ParameterMode.IN)
            .registerStoredProcedureParameter("p_correo", String.class, ParameterMode.IN)
            .registerStoredProcedureParameter("p_salario", String.class, ParameterMode.IN)
            .registerStoredProcedureParameter("p_fecha_ingreso", String.class, ParameterMode.IN)
            .registerStoredProcedureParameter("p_fecha_nacimiento", String.class, ParameterMode.IN)
            .setParameter("p_id_empleado", IdEmpleado)
            .setParameter("p_nombre", nombre)
            .setParameter("p_apellido", apellido)
            .setParameter("p_direccion", direccion)
            .setParameter("p_num_telefono", numTelefono)
            .setParameter("p_correo", correoElect)
            .setParameter("p_salario", salario)
            .setParameter("p_fecha_ingreso", FechaIngre)
            .setParameter("p_fecha_nacimiento", FechaNac);

    query.execute();
}

@Transactional
@Override
    public void insertarEmpleado(Long IdEmpleado, String nombre,
            String apellido,
            String direccion,
            String numTelefono,
            String correoElect,
            String salario,
            String FechaIngre,
            String FechaNac) {
    StoredProcedureQuery query = entityManager.createStoredProcedureQuery("insertar_empleado")
            .registerStoredProcedureParameter("p_id_empleado", Long.class, ParameterMode.IN)
            .registerStoredProcedureParameter("p_nombre", String.class, ParameterMode.IN)
            .registerStoredProcedureParameter("p_apellido", String.class, ParameterMode.IN)
            .registerStoredProcedureParameter("p_direccion", String.class, ParameterMode.IN)
            .registerStoredProcedureParameter("p_num_telefono", String.class, ParameterMode.IN)
            .registerStoredProcedureParameter("p_correo", String.class, ParameterMode.IN)
            .registerStoredProcedureParameter("p_salario", String.class, ParameterMode.IN)
            .registerStoredProcedureParameter("p_fecha_ingreso", String.class, ParameterMode.IN)
            .registerStoredProcedureParameter("p_fecha_nacimiento", String.class, ParameterMode.IN)
            .setParameter("p_id_empleado", IdEmpleado)
            .setParameter("p_nombre", nombre)
            .setParameter("p_apellido", apellido)
            .setParameter("p_direccion", direccion)
            .setParameter("p_num_telefono", numTelefono)
            .setParameter("p_correo", correoElect)
            .setParameter("p_salario", salario)
            .setParameter("p_fecha_ingreso", FechaIngre)
            .setParameter("p_fecha_nacimiento", FechaNac);

    query.execute();
}

@Override
public Long ObtenerUltimoEmpleado() {
    StoredProcedureQuery query = entityManager.createStoredProcedureQuery("ObtenerUltimoEmpleado")
            .registerStoredProcedureParameter("p_id_empleado", Long.class, ParameterMode.OUT);

    query.execute();

    return (Long) query.getOutputParameterValue("p_id_empleado") + 1;
}

}
