package com.Proyecto;


import com.Proyecto.domain.Empleados;
import com.Proyecto.domain.vValoracion;
import com.Proyecto.domain.vVehiculos;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

@SpringBootApplication
public class ProyectoApplication implements CommandLineRunner/**/ {

   
    @Autowired
    private JdbcTemplate jdbcTemplate ;
     
     public static void main(String[] args) {
        SpringApplication.run(ProyectoApplication.class, args);
    }
 
     /*
     
     
     */
    
   @Override
    public void run(String... args) throws Exception {

        String sql = "SELECT * FROM VISTA_CLIENTES";
         
        List<vValoracion> students = jdbcTemplate.query(sql,
               BeanPropertyRowMapper.newInstance(vValoracion.class));
         
        students.forEach(System.out :: println);
    }

}
