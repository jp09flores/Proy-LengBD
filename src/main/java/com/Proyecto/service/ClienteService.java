package com.Proyecto.service;

import com.Proyecto.domain.Cliente;
import com.Proyecto.domain.Cursores;
import java.math.BigDecimal;
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
   
    public int eliminarCliente(Long idCliente);
    
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
    
    public BigDecimal obtenerTotalClientes();
    
    public String obtenerClienteMasValorado();
    
    public Cursores Cursor(String username);
 
}
