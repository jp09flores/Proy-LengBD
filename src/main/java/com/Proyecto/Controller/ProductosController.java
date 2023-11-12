package com.Proyecto.Controller;
import com.Proyecto.domain.Productos;
import com.Proyecto.service.ProductosService;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/productos")
@Slf4j
public class ProductosController {

    @Autowired
    private ProductosService productoService;

    @GetMapping("/listado")
    public String listado(Model model) {
        List<Productos> productos = productoService.getProductos();
        model.addAttribute("productos", productos);
        return "productos/listado";
    }

    @GetMapping("/modificar/{IdTipoProducto}")
    public String obtenerProducto(@PathVariable Long IdTipoProducto, Model model) {
        Productos producto = productoService.SeleccionarProductos(IdTipoProducto);
        Long producto_id = IdTipoProducto;
        model.addAttribute("producto", producto);
        model.addAttribute("IdTipoProducto", producto_id);
        return "productos/modificar";
    }

    @PostMapping("/actualizar")
    public String actualizarProducto(@RequestParam Long IdTipoProducto, @RequestParam String nombre,
         @RequestParam String detalles) {
        productoService.actualizarProductos(IdTipoProducto,nombre, detalles);
        return "redirect:/productos/listado";
    }

    @GetMapping("/eliminar/{IdTipoProducto}")
    public String eliminarProducto(@PathVariable Long IdTipoProducto) {
        productoService.eliminarTipoProducto(IdTipoProducto);
        return "redirect:/productos/listado";
    }

    @GetMapping("/agregar")
    public String agregar(Model model) {
        Long id = productoService.ObtenerUltimoProducto();
        model.addAttribute("idUltimoProducto", id);
        return "productos/agregar";
    }

    @PostMapping("/guardar")
    public String guardar(@RequestParam Long IdTipoProducto, @RequestParam String nombre,
        @RequestParam String detalles) {
        productoService.insertarProductos(IdTipoProducto, nombre, detalles);
        return "redirect:/productos/listado";
    }
}
