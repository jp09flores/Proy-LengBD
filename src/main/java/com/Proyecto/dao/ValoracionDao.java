/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.Proyecto.dao;

import com.Proyecto.domain.Valoracion;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author jp09f
 */
public interface ValoracionDao extends JpaRepository<Valoracion,Long>{
    
}
