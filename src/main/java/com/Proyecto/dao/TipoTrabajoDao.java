
package com.Proyecto.dao;

import com.Proyecto.domain.TipoTrabajo;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface TipoTrabajoDao extends JpaRepository<TipoTrabajo,Long> {
    
}
