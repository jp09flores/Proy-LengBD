package com.Proyecto;

import com.Proyecto.domain.Countries;
import com.Proyecto.domain.Usuario;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

@SpringBootApplication
public class ProyectoApplication implements CommandLineRunner {

   
    @Autowired
    private JdbcTemplate jdbcTemplate ;
     
     public static void main(String[] args) {
        SpringApplication.run(ProyectoApplication.class, args);
    }
 
    @Override
    public void run(String... args) throws Exception {
        String sql = "SELECT * FROM countries";
         
        List<Countries> students = jdbcTemplate.query(sql,
                BeanPropertyRowMapper.newInstance(Countries.class));
         
        students.forEach(System.out :: println);
    }
   

}
