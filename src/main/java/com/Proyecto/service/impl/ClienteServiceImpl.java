package com.Proyecto.service.impl;

import com.Proyecto.dao.ClienteDao;
import com.Proyecto.domain.Cliente;
import com.Proyecto.service.ClienteService;
import jakarta.persistence.EntityManager;
import jakarta.persistence.ParameterMode;
import jakarta.persistence.StoredProcedureQuery;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ClienteServiceImpl implements ClienteService {

    @Autowired
    ClienteDao clienteDao;

    @Autowired
    private EntityManager entityManager;

    @Override
    @Transactional(readOnly = true)
    public List<Cliente> getClientes() {
        List<Cliente> Cliente = clienteDao.findAll();
        return Cliente;
    }

    @Override
    @Transactional
    public Cliente seleccionarCliente(Long idCliente) {
        StoredProcedureQuery query = entityManager.createStoredProcedureQuery("seleccionar_cliente")
                .registerStoredProcedureParameter("p_id_cliente", Long.class, ParameterMode.IN)
                .registerStoredProcedureParameter("p_username", String.class, ParameterMode.OUT)
                .registerStoredProcedureParameter("p_contrasena", String.class, ParameterMode.OUT)
                .registerStoredProcedureParameter("p_nombre", String.class, ParameterMode.OUT)
                .registerStoredProcedureParameter("p_apellido", String.class, ParameterMode.OUT)
                .registerStoredProcedureParameter("p_direccion", String.class, ParameterMode.OUT)
                .registerStoredProcedureParameter("p_num_telefono", String.class, ParameterMode.OUT)
                .registerStoredProcedureParameter("p_correo_elect", String.class, ParameterMode.OUT)
                .setParameter("p_id_cliente", idCliente);

        query.execute();

        String username = (String) query.getOutputParameterValue("p_username");
        String contrasena = (String) query.getOutputParameterValue("p_contrasena");
        String nombre = (String) query.getOutputParameterValue("p_nombre");
        String apellido = (String) query.getOutputParameterValue("p_apellido");
        String direccion = (String) query.getOutputParameterValue("p_direccion");
        String numTelefono = (String) query.getOutputParameterValue("p_num_telefono");
        String correoElectronico = (String) query.getOutputParameterValue("p_correo_elect");

        return new Cliente(idCliente, username, contrasena, nombre, apellido, direccion, numTelefono, correoElectronico);
    }

    @Override
    @Transactional
    public void eliminarCliente(Long idCliente) {
        StoredProcedureQuery query = entityManager.createStoredProcedureQuery("eliminar_cliente")
                .registerStoredProcedureParameter("p_id_cliente", Long.class, ParameterMode.IN)
                .setParameter("p_id_cliente", idCliente);

        query.execute();
    }

    @Override
    @Transactional
    public void actualizarCliente(Long idCliente, String username, String contrasena, String nombre, String apellido, String direccion, String numTelefono, String correoElectronico) {
        StoredProcedureQuery query = entityManager.createStoredProcedureQuery("actualizar_cliente")
                .registerStoredProcedureParameter("p_id_cliente", Long.class, ParameterMode.IN)
                .registerStoredProcedureParameter("p_username", String.class, ParameterMode.IN)
                .registerStoredProcedureParameter("p_contrasena", String.class, ParameterMode.IN)
                .registerStoredProcedureParameter("p_nombre", String.class, ParameterMode.IN)
                .registerStoredProcedureParameter("p_apellido", String.class, ParameterMode.IN)
                .registerStoredProcedureParameter("p_direccion", String.class, ParameterMode.IN)
                .registerStoredProcedureParameter("p_num_telefono", String.class, ParameterMode.IN)
                .registerStoredProcedureParameter("p_correo_elect", String.class, ParameterMode.IN)
                .setParameter("p_id_cliente", idCliente)
                .setParameter("p_username", username)
                .setParameter("p_contrasena", contrasena)
                .setParameter("p_nombre", nombre)
                .setParameter("p_apellido", apellido)
                .setParameter("p_direccion", direccion)
                .setParameter("p_num_telefono", numTelefono)
                .setParameter("p_correo_elect", correoElectronico);

        query.execute();
    }

    @Override
    @Transactional
    public void insertarCliente(Long idCliente, String username, String contrasena, String nombre, String apellido, String direccion, String numTelefono, String correoElectronico) {
        StoredProcedureQuery query = entityManager.createStoredProcedureQuery("insertar_cliente")
                .registerStoredProcedureParameter("p_id_cliente", Long.class, ParameterMode.IN)
                .registerStoredProcedureParameter("p_username", String.class, ParameterMode.IN)
                .registerStoredProcedureParameter("p_contrasena", String.class, ParameterMode.IN)
                .registerStoredProcedureParameter("p_nombre", String.class, ParameterMode.IN)
                .registerStoredProcedureParameter("p_apellido", String.class, ParameterMode.IN)
                .registerStoredProcedureParameter("p_direccion", String.class, ParameterMode.IN)
                .registerStoredProcedureParameter("p_num_telefono", String.class, ParameterMode.IN)
                .registerStoredProcedureParameter("p_correo_elect", String.class, ParameterMode.IN)
                .setParameter("p_id_cliente", idCliente)
                .setParameter("p_username", username)
                .setParameter("p_contrasena", contrasena)
                .setParameter("p_nombre", nombre)
                .setParameter("p_apellido", apellido)
                .setParameter("p_direccion", direccion)
                .setParameter("p_num_telefono", numTelefono)
                .setParameter("p_correo_elect", correoElectronico);

        query.execute();
    }

    @Transactional
    public Long obtenerUltimoCliente() {
        StoredProcedureQuery query = entityManager.createStoredProcedureQuery("ObtenerUltimoCliente")
                .registerStoredProcedureParameter("p_id_cliente", Long.class, ParameterMode.OUT);

        query.execute();

        return (Long) query.getOutputParameterValue("p_id_cliente")+1;
    }
}  

