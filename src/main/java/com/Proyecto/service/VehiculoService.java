package com.Proyecto.service;

import com.Proyecto.domain.Cursores;
import com.Proyecto.domain.Vehiculo;
import java.util.List;

public interface VehiculoService {
    
    public List<Vehiculo> getTiposVehiculos();
    
    public Vehiculo seleccionarVehiculo(String numPlaca);
    
    public void actualizarVehiculo(
            String numPlaca,
            String numMotor,
            Long idCliente,
            String marca,
            String color,
            String modelo,
            String ano);
    
    public int eliminarVehiculo(String numPlaca);
     
    public void insertarVehiculo(
            String numPlaca,
            String numMotor,
            Long idCliente,
            String marca,
            String color,
            String modelo,
            String year);
    public Cursores Cursor(char placa);
}
