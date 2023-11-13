
package com.Proyecto.dao;

import com.Proyecto.domain.Cliente;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ClienteDao extends JpaRepository<Cliente,Long> {
    
}
