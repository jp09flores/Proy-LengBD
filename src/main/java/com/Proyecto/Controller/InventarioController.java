/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/springframework/Controller.java to edit this template
 */
package com.Proyecto.Controller;

import com.Proyecto.domain.Inventario;
import com.Proyecto.domain.TipoProducto;
import com.Proyecto.service.InventarioService;
import com.Proyecto.service.TipoProductoService;

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
@RequestMapping("/inventario")
@Slf4j
public class InventarioController {
    
    @Autowired
    private InventarioService inventarioService;
    @Autowired
    private TipoProductoService tipoProductoService;
    
   @GetMapping("/listado")
    public String listado(Model model) {
        List<Inventario> inventario = inventarioService.getInventario();
        model.addAttribute("inventario", inventario);
          String productoMasAntiguo = inventarioService.obtenerProductoMasAntiguo();
         model.addAttribute("productoMasAntiguo", productoMasAntiguo); 
          
        return "inventario/listado";
    }
    
    @GetMapping("/modificar/{id}")
    public String obtenerProductoInventario(@PathVariable Long id, Model model) {
        Inventario producto = inventarioService.seleccionarProducto(id);
        Long productoid = id;
        model.addAttribute("producto", producto);
        model.addAttribute("productoid", productoid);
         List<TipoProducto> tipoProductoLista = tipoProductoService.getProductos();
        model.addAttribute("tipoProductoLista", tipoProductoLista);
        return "inventario/modificar";
    }
    
    @PostMapping("/actualizar")
    public String actualizarInventario(@RequestParam Long idProducto, @RequestParam Long idTipoProducto, @RequestParam String nombre, 
            @RequestParam String fechaIngre, @RequestParam Integer stock, @RequestParam String detalles) {
        inventarioService.actualizarProducto(idProducto, idTipoProducto, nombre, fechaIngre, stock, detalles);
        return "redirect:/inventario/listado";
    }
    @GetMapping("/eliminar/{id}")
    public String eliminarProducto(@PathVariable Long id) {
        inventarioService.eliminarProducto(id);
         return "redirect:/inventario/listado";
    }
    
    
     @GetMapping("/agregar")
    public String agregar(Model model) {
          List<TipoProducto> tipoProductoLista = tipoProductoService.getProductos();
        model.addAttribute("tipoProductoLista", tipoProductoLista);
       Long id = inventarioService.obtenerUltimoProducto();
       model.addAttribute("idUltimoProducto", id);
        return "inventario/agregar";
    }
    @PostMapping("/guardar")
    public String guardar(@RequestParam Long idProducto, @RequestParam Long idTipoProducto, @RequestParam String nombre, 
            @RequestParam String fechaIngre, @RequestParam Integer stock, @RequestParam String detalles) {
        inventarioService.insertarProducto(idProducto, idTipoProducto, nombre, fechaIngre, stock, detalles);
        return "redirect:/inventario/listado";
    }
    
}
