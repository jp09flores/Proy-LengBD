package com.Proyecto.service;

import com.Proyecto.domain.Cliente;
import java.util.List;

public interface ClienteService {
    
    public List<Cliente> getClientes();
    
    public Cliente seleccionarCliente(Long idCliente);
    
    public void actualizarCliente(
            Long idCliente,
            String username, 
            String contrasena,
            String nombre,
            String apellido,
            String direccion,
            String numTelefono,
            String correoElectronico);
   
    public void eliminarCliente(Long idCliente);
    
    public Long obtenerUltimoCliente();
     
    public void insertarCliente(
            Long idCliente,
            String username,
            String contrasena,
            String nombre,
            String apellido,
            String direccion,
            String numtelefono,
            String correoElect);
 
}
