/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.Proyecto.service;

import com.Proyecto.domain.Usuario;
import java.util.List;

/**
 *
 * @author jp09f
 */
public interface UsuarioService {
    public List<Usuario> getUsuarios();
    
    
    public Usuario seleccionarUsuario(Long id);
    
    public void actualizarUsuario(Long id, String nombre, String correo);
    
    public void eliminarUsuario(Long id);
    
    public Long obtenerUltimoUsuario();
     
    public void insertarUsuario(Long id, String nombre, String correo);
}
