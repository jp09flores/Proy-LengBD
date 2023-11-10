/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.Proyecto.service.impl;

import com.Proyecto.dao.UsuarioDao;
import com.Proyecto.domain.Usuario;
import com.Proyecto.service.UsuarioService;
import jakarta.persistence.EntityManager;
import jakarta.persistence.ParameterMode;
import jakarta.persistence.StoredProcedureQuery;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author jp09f
 */
@Service
public class UsuariosServiceImpl implements UsuarioService{
    @Autowired
    UsuarioDao usuarioDao;


    @Override
    @Transactional(readOnly = true)
    public List<Usuario> getUsuarios() {
         List<Usuario> Usuario = usuarioDao.findAll();
        return Usuario;
    }
    
     @Autowired
    private EntityManager entityManager;
     
    
    @Override
    public Usuario seleccionarUsuario(Long id) {
        StoredProcedureQuery query = entityManager.createStoredProcedureQuery("seleccionar_usuario")
                .registerStoredProcedureParameter("p_id", Long.class, ParameterMode.IN)
                .registerStoredProcedureParameter("p_nombre", String.class, ParameterMode.OUT)
                .registerStoredProcedureParameter("p_correo", String.class, ParameterMode.OUT)
                .setParameter("p_id", id);

        query.execute();

        String nombre = (String) query.getOutputParameterValue("p_nombre");
        String correo = (String) query.getOutputParameterValue("p_correo");

        Usuario usuario = new Usuario();
        usuario.setNombre(nombre);
        usuario.setCorreo(correo);

        return usuario;
    }
    
    @Override
    @Transactional
    public void actualizarUsuario(Long id, String nombre, String correo) {
        entityManager.createStoredProcedureQuery("actualizar_usuario")
                .registerStoredProcedureParameter("p_id", Long.class, ParameterMode.IN)
                .registerStoredProcedureParameter("p_nombre", String.class, ParameterMode.IN)
                .registerStoredProcedureParameter("p_correo", String.class, ParameterMode.IN)
                .setParameter("p_id", id)
                .setParameter("p_nombre", nombre)
                .setParameter("p_correo", correo)
                .execute();
    }
    
    @Override
    @Transactional
    public void eliminarUsuario(Long id) {
        entityManager.createStoredProcedureQuery("eliminar_usuario")
                .registerStoredProcedureParameter("p_id", Long.class, ParameterMode.IN)
                .setParameter("p_id", id)
                .execute();
    }
    
    @Override
    public Long obtenerUltimoUsuario() {
        StoredProcedureQuery query = entityManager.createStoredProcedureQuery("ObtenerUltimoUsuario")
                .registerStoredProcedureParameter("p_id", Long.class, ParameterMode.OUT);

        query.execute();

        Long ultimoID = (Long) query.getOutputParameterValue("p_id");

        return ultimoID +1;
    }
    
    @Override
    @Transactional
    public void insertarUsuario(Long id, String nombre, String correo) {
        entityManager.createStoredProcedureQuery("insertar_usuario")
                .registerStoredProcedureParameter("p_id", Long.class, ParameterMode.IN)
                .registerStoredProcedureParameter("p_nombre", String.class, ParameterMode.IN)
                .registerStoredProcedureParameter("p_correo", String.class, ParameterMode.IN)
                .setParameter("p_id", id)
                .setParameter("p_nombre", nombre)
                .setParameter("p_correo", correo)
                .execute();
    }
}
