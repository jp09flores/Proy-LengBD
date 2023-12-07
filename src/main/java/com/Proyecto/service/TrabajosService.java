package com.Proyecto.service;

import com.Proyecto.domain.Trabajos;
import java.util.Date;
import java.util.List;

public interface TrabajosService {

    
    List<Trabajos> getTrabajos();

    Trabajos seleccionarTrabajo(Long idTrabajo);

    int eliminarTrabajo(Long idTrabajo);

    void actualizarTrabajo(Long idTrabajo, Long idTipoTrabajo, String fecha, Long idCliente, String numPlaca, Integer cantProductos, Long idEmpleado);

    void insertarTrabajo(Long idTrabajo, Long idTipoTrabajo, String fecha, Long idCliente, String numPlaca, Integer cantProductos, Long idEmpleado);

    Long obtenerUltimoTrabajo();
    
    public String obtenerUltimaFecha();

}
