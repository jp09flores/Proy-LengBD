/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/springframework/Controller.java to edit this template
 */
package com.Proyecto.Controller;

import com.Proyecto.dao.vProveedoresDao;
import com.Proyecto.domain.Cursores;
import com.Proyecto.domain.Proveedores;
import com.Proyecto.domain.vProveedores;
import com.Proyecto.service.ProveedoresService;
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

@Controller
@RequestMapping("/proveedores")
@Slf4j
public class ProveedoresController {

    @Autowired
    private ProveedoresService proveedoresService;

    @Autowired
    private vProveedoresDao vproveedoresDao;

    @Autowired
    public ProveedoresController(ProveedoresService proveedoresService) {
        this.proveedoresService = proveedoresService;
    }

    @GetMapping("/listado")
    public String listado(Model model) {
        List<Proveedores> proveedores = proveedoresService.getProveedores();
        model.addAttribute("proveedores", proveedores);

        List<vProveedores> vProveedores = vproveedoresDao.findAll();
        model.addAttribute("vProveedores", vProveedores);
        return "proveedores/listado";
    }

    @GetMapping("/modificar/{idProveedor}")
    public String obtenerProveedor(@PathVariable Long idProveedor, Model model) {
        Proveedores proveedores = proveedoresService.SeleccionarProveedor(idProveedor);
        Long proveedor_id = idProveedor;
        model.addAttribute("proveedor", proveedores);
        model.addAttribute("idProveedor", proveedor_id);
        return "proveedores/modificar";
    }

    @PostMapping("/actualizar")
    public String actualizarProveedor(@RequestParam Long idProveedor, @RequestParam String nombre, @RequestParam String numTelefono, @RequestParam String direccion, @RequestParam String detalles) {
        proveedoresService.actualizarProveedor(idProveedor, nombre, numTelefono, direccion, detalles);
        return "redirect:/proveedores/listado";
    }

    @GetMapping("/eliminar/{idProveedor}")
    public String eliminarProveedor(@PathVariable Long idProveedor) {
        proveedoresService.eliminarProveedor(idProveedor);
        return "redirect:/proveedores/listado";
    }

    @GetMapping("/agregar")
    public String agregar(Model model) {
        Long id = proveedoresService.ObtenerUltimoProveedor();
        model.addAttribute("idUltimoProveedor", id);
        return "proveedores/agregar";
    }

    @PostMapping("/guardar")
    public String guardar(@RequestParam Long idProveedor, @RequestParam String nombre, @RequestParam String numTelefono, @RequestParam String direccion, @RequestParam String detalles) {
        proveedoresService.insertarProveedor(idProveedor, nombre, numTelefono, direccion, detalles);
        return "redirect:/proveedores/listado";
    }

    @GetMapping("/cursor/{idProveedor}")
    public String obtenerProveedorCursor(@PathVariable Long idProveedor, Model model) {
        Cursores cursores = proveedoresService.obtenerProveedorCursor(idProveedor);
        model.addAttribute("proveedorDetails", cursores.getOutput());
        return "proveedores/cursor";
    }
}
