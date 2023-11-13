package com.Proyecto.dao;

import com.Proyecto.domain.Vehiculo;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface VehiculoDao extends JpaRepository<Vehiculo,String>{
    
}
