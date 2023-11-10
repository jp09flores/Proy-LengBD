/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/springframework/Controller.java to edit this template
 */
package com.Proyecto.Controller;


import com.Proyecto.domain.Usuario;

import com.Proyecto.service.UsuarioService;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author jp09f
 */
@Controller
@Slf4j
public class IndexController {
    
    
    @Autowired
    private UsuarioService usuarioService;
    
    @RequestMapping("/")
    public String page(Model model) {
        return "index";
    }
    
}
