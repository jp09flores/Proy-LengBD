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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author jp09f
 */
@Controller
@RequestMapping("/usuario")
@Slf4j
public class UsuarioController {
    
    
    
    @Autowired
    private UsuarioService usuarioService;
    
   @GetMapping("/listado")
    public String listado(Model model) {
        List<Usuario> usuarios = usuarioService.getUsuarios();
        model.addAttribute("usuarios", usuarios);
        return "usuario/listado";
    }
    
    @GetMapping("/modificar/{id}")
    public String obtenerUsuario(@PathVariable Long id, Model model) {
        Usuario usuario = usuarioService.seleccionarUsuario(id);
        Long usuario_id = id;
        model.addAttribute("usuario", usuario);
        model.addAttribute("usuario_id", usuario_id);
        return "usuario/modificar";
    }
    
    @PostMapping("/actualizar")
    public String actualizarUsuario(@RequestParam Long id, @RequestParam String nombre, @RequestParam String correo) {
        usuarioService.actualizarUsuario(id, nombre, correo);
        return "redirect:/usuario/listado";
    }
    @GetMapping("/eliminar/{id}")
    public String eliminarUsuario(@PathVariable Long id) {
        usuarioService.eliminarUsuario(id);
         return "redirect:/usuario/listado";
    }
    
    
     @GetMapping("/agregar")
    public String agregar(Model model) {
       Long id = usuarioService.obtenerUltimoUsuario();
       model.addAttribute("idUltimoUsuario", id);
        return "usuario/agregar";
    }
    @PostMapping("/guardar")
    public String guardar(@RequestParam Long id, @RequestParam String nombre, @RequestParam String correo) {
        usuarioService.insertarUsuario(id, nombre, correo);
        return "redirect:/usuario/listado";
    }
}
