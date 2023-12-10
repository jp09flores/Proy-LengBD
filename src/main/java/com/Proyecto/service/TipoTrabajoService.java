package com.Proyecto.service;

import com.Proyecto.domain.Cursores;
import com.Proyecto.domain.TipoTrabajo;
import java.util.List;

public interface TipoTrabajoService {
    
    public List<TipoTrabajo> getTiposTrabajos();
    
    public TipoTrabajo seleccionarTipoTrabajo(Long idTipoTrabajo);
    
    public void actualizarTipoTrabajo(
            Long idTipoTrabajo,
            String nombre,
            String requisitos,
            String contenido,
            String detalles);
    
    public void eliminarTipoTrabajo(Long idTipoTrabajo);
    
    public Long obtenerUltimoTipoTrabajo();
     
    public void insertarTipoTrabajo(
            Long idTipoTrabajo,
            String nombre,
            String requisitos,
            String contenido,
            String detalles);
    
    
     public String obtenerUltimoTrabajo();
     public Cursores Cursor(char letraInicial);
}
