/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/springframework/Controller.java to edit this template
 */
package com.Proyecto.Controller;

import com.Proyecto.dao.vValoracionDao;
import com.Proyecto.domain.Cliente;
import com.Proyecto.domain.Valoracion;
import com.Proyecto.domain.vValoracion;
import com.Proyecto.service.ClienteService;
import com.Proyecto.service.ValoracionService;
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
@RequestMapping("/valoracion")
@Slf4j
public class ValoracionController {
    @Autowired
    private ClienteService clienteService;
    @Autowired
    private ValoracionService valoracionService;
    @Autowired
    private vValoracionDao vvaloraciondao;
   @GetMapping("/listado")
    public String listado(Model model) {
        List<Valoracion> valoracion = valoracionService.getValoraciones();
        model.addAttribute("valoraciones", valoracion);
         List<vValoracion> vValoracion = vvaloraciondao.findAll();
        model.addAttribute("vValoracion", vValoracion);
        return "valoracion/listado";
    }
    
    @GetMapping("/modificar/{id}")
    public String obtenerValoracion(@PathVariable Long id, Model model) {
        Valoracion valoracion = valoracionService.seleccionarValoracion(id);
        Long valoracion_id = id;
        model.addAttribute("valoracion", valoracion);
        model.addAttribute("valoracion_id", valoracion_id);
         List<Cliente> clientes = clienteService.getClientes();
        model.addAttribute("clientes", clientes);
        return "valoracion/modificar";
    }
    
    @PostMapping("/actualizar")
   public String actualizarValoracion(@RequestParam Long idValoracion, @RequestParam Long idCliente, @RequestParam String comentario, @RequestParam Integer valoracion, @RequestParam String fechaEmi) {
        valoracionService.actualizarValoracion(idValoracion, idCliente, comentario, valoracion, fechaEmi);
        return "redirect:/valoracion/listado";
    }
    @GetMapping("/eliminar/{id}")
    public String eliminarValoracion(@PathVariable Long id) {
        valoracionService.eliminarValoracion(id);
         return "redirect:/valoracion/listado";
    }
    
    
     @GetMapping("/agregar")
    public String agregar(Model model) {
          List<Cliente> clientes = clienteService.getClientes();
        model.addAttribute("clientes", clientes);
       Long id = valoracionService.obtenerUltimaValoracion();
       model.addAttribute("idUltimoValoracion", id);
        return "valoracion/agregar";
    }
    @PostMapping("/guardar")
  public String guardar(@RequestParam Long idValoracion, @RequestParam Long idCliente, @RequestParam String comentario, @RequestParam Integer valoracion, @RequestParam String fechaEmi) {
        valoracionService.insertarValoracion(idValoracion, idCliente, comentario, valoracion, fechaEmi);
        return "redirect:/valoracion/listado";
    }
    
}
