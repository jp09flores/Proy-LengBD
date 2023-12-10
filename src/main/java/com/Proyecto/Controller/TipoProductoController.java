package com.Proyecto.Controller;

import com.Proyecto.domain.Cursores;
import com.Proyecto.domain.TipoProducto;
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
import com.Proyecto.service.TipoProductoService;

@Controller
@RequestMapping("/tipoProducto")
@Slf4j
public class TipoProductoController {

    @Autowired
    private TipoProductoService productoService;

    @GetMapping("/listado")
    public String listado(Model model) {
        List<TipoProducto> tipoProductos = productoService.getProductos();
        model.addAttribute("tipoProductos", tipoProductos);
        Cursores cursores = productoService.Cursor();
        model.addAttribute("cursor", cursores.getOutput());
        return "tipoProducto/listado";
    }

    @GetMapping("/modificar/{IdTipoProducto}")
    public String obtenerProducto(@PathVariable Long IdTipoProducto, Model model) {
        TipoProducto tipoProducto = productoService.SeleccionarProductos(IdTipoProducto);
        Long tipoProductoId = IdTipoProducto;
        model.addAttribute("tipoProducto", tipoProducto);
        model.addAttribute("IdTipoProducto", tipoProductoId);
        return "tipoProducto/modificar";
    }

    @PostMapping("/actualizar")
    public String actualizarProducto(@RequestParam Long IdTipoProducto, @RequestParam String nombre,
            @RequestParam String detalles) {
        productoService.actualizarProductos(IdTipoProducto, nombre, detalles);
        return "redirect:/tipoProducto/listado";
    }

    @GetMapping("/eliminar/{IdTipoProducto}")
    public String eliminarProducto(@PathVariable Long IdTipoProducto) {
        productoService.eliminarTipoProducto(IdTipoProducto);
        return "redirect:/tipoProducto/listado";
    }

    @GetMapping("/agregar")
    public String agregar(Model model) {
        Long id = productoService.ObtenerUltimoProducto();
        model.addAttribute("idUltimoProducto", id);
        return "tipoProducto/agregar";
    }

    @PostMapping("/guardar")
    public String guardar(@RequestParam Long IdTipoProducto, @RequestParam String nombre,
            @RequestParam String detalles) {
        productoService.insertarProductos(IdTipoProducto, nombre, detalles);
        return "redirect:/tipoProducto/listado";
    }
}
