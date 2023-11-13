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
public class ClienteServiceImpl implements ClienteService{
    
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
    public Cliente seleccionarCliente(Long idCliente) {
        StoredProcedureQuery query = entityManager.createStoredProcedureQuery("seleccionar_cliente")
                .registerStoredProcedureParameter("c_id", Long.class, ParameterMode.IN)
                .registerStoredProcedureParameter("c_username", String.class, ParameterMode.OUT)
                .registerStoredProcedureParameter("c_nombre", String.class, ParameterMode.OUT)
                .registerStoredProcedureParameter("c_apellido", String.class, ParameterMode.OUT)
                .registerStoredProcedureParameter("c_direccion", String.class, ParameterMode.OUT)
                .registerStoredProcedureParameter("c_telefono", String.class, ParameterMode.OUT)
                .registerStoredProcedureParameter("c_correo", String.class, ParameterMode.OUT)
                .setParameter("c_id", idCliente);
        
        query.execute();
        
        String username = (String) query.getOutputParameterValue("c_username");
        String nombre = (String) query.getOutputParameterValue("c_nombre");
        String apellido = (String) query.getOutputParameterValue("c_apellido");
        String direccion = (String) query.getOutputParameterValue("c_direccion");
        String numTelefono = (String) query.getOutputParameterValue("c_num_telefono");
        String correoElect = (String) query.getOutputParameterValue("c_correo_elect");
        
        Cliente cliente = new Cliente();
        cliente.setUsername(username);
        cliente.setNombre(nombre);
        cliente.setApellido(apellido);
        cliente.setDireccion(direccion);
        cliente.setNumTelefono(numTelefono);
        cliente.setCorreoElect(correoElect);
        return cliente;
    }

    @Override
    @Transactional
    public void actualizarCliente(Long idCliente, String username, String correoElect) {
        entityManager.createStoredProcedureQuery("actualizar_cliente")
                .registerStoredProcedureParameter("c_id", Long.class, ParameterMode.IN)
                .registerStoredProcedureParameter("c_username", String.class, ParameterMode.IN)
                .registerStoredProcedureParameter("c_correo", String.class, ParameterMode.IN)
                .setParameter("c_id", idCliente)
                .setParameter("c_username", username)
                .setParameter("c_correo", correoElect)
                .execute();
    }

    @Override
    @Transactional
    public void eliminarCliente(Long idCliente) {
        entityManager.createStoredProcedureQuery("eliminar_cliente")
                .registerStoredProcedureParameter("c_id", Long.class, ParameterMode.IN)
                .setParameter("c_id", idCliente)
                .execute();
    }

    @Override
    public Long obtenerUltimoCliente() {
        StoredProcedureQuery query = entityManager.createStoredProcedureQuery("ObtenerUltimoCliente")
                .registerStoredProcedureParameter("c_id", Long.class, ParameterMode.OUT);

        query.execute();

        Long ultimoID = (Long) query.getOutputParameterValue("c_id");

        return ultimoID +1;    
    }

    @Override
    public void insertarCliente(Long idCliente, String username, String contrasena, String nombre, String apellido, String direccion, String numTelefono, String correElect) {
        entityManager.createStoredProcedureQuery("insertar_cliente")
                .registerStoredProcedureParameter("c_id", Long.class, ParameterMode.IN)
                .registerStoredProcedureParameter("c_username", String.class, ParameterMode.IN)
                .registerStoredProcedureParameter("c_contra", String.class, ParameterMode.IN)
                .registerStoredProcedureParameter("c_nombre", String.class, ParameterMode.IN)
                .registerStoredProcedureParameter("c_apellido", String.class, ParameterMode.IN)
                .registerStoredProcedureParameter("c_direccion", String.class, ParameterMode.IN)
                .registerStoredProcedureParameter("c_telefono", String.class, ParameterMode.IN)
                .registerStoredProcedureParameter("c_correo", String.class, ParameterMode.IN)
                
                .setParameter("c_id", idCliente)
                .setParameter("c_username", username)
                .setParameter("c_contra", username)
                .setParameter("c_nombre", nombre)
                .setParameter("c_apellido", apellido)
                .setParameter("c_direccion", direccion)
                .setParameter("c_telefono", numTelefono)
                .setParameter("c_correo", correElect)
                .execute();
    }   
}