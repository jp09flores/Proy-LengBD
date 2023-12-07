/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/springframework/Controller.java to edit this template
 */
package com.Proyecto.Controller;

import com.Proyecto.dao.vTrabajosDao;
import com.Proyecto.domain.Cliente;
import com.Proyecto.domain.Empleados;
import com.Proyecto.domain.TipoTrabajo;
import com.Proyecto.domain.Trabajos;
import com.Proyecto.domain.Usuario;
import com.Proyecto.domain.VTrabajos;
import com.Proyecto.domain.Vehiculo;
import com.Proyecto.service.ClienteService;
import com.Proyecto.service.EmpleadosService;
import com.Proyecto.service.TipoTrabajoService;

import com.Proyecto.service.TrabajosService;
import com.Proyecto.service.UsuarioService;
import com.Proyecto.service.VehiculoService;
import java.util.Date;

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
@RequestMapping("/trabajos")
@Slf4j
public class TrabajosController {
  
    @Autowired
    private TrabajosService trabajosService;
    @Autowired
    private EmpleadosService empleadosService;
    @Autowired
    private TipoTrabajoService tipoTrabajoService;
    
    @Autowired
    private VehiculoService vehiculoService;
    
    @Autowired
    private ClienteService clienteService;
    
      @Autowired
    private vTrabajosDao vTrabajosDao;
       
   @GetMapping("/listado")
    public String listado(Model model) {
        List<Trabajos> trabajos = trabajosService.getTrabajos();
        model.addAttribute("trabajos", trabajos);
        
     List<VTrabajos> VTrabajos = vTrabajosDao.findAll();
         model.addAttribute("VTrabajos", VTrabajos);
        
        String ultimaFecha = trabajosService.obtenerUltimaFecha();
        model.addAttribute("ultimaFecha", ultimaFecha);
        return "trabajos/listado";
    }
    
    @GetMapping("/modificar/{id}")
    public String obtenerTrabajos(@PathVariable Long id, Model model) {
        Trabajos trabajo = trabajosService.seleccionarTrabajo(id);
        Long trabajoid = id;
        model.addAttribute("trabajo", trabajo);
        model.addAttribute("trabajoid", trabajoid);
          List<Empleados> empleados = empleadosService.getEmpleados();
        model.addAttribute("empleados", empleados);
        List<Cliente> clientes = clienteService.getClientes();
        model.addAttribute("clientes", clientes);
          List<TipoTrabajo> tipoTrabajolista = tipoTrabajoService.getTiposTrabajos();
        model.addAttribute("tipoTrabajolista", tipoTrabajolista);
         List<Vehiculo> vehiculos = vehiculoService.getTiposVehiculos();
        model.addAttribute("vehiculos", vehiculos);
       model.addAttribute("idUltimoTrabajo", id);
       
        return "trabajos/modificar";
    }
    
    @PostMapping("/actualizar")
    public String actualizarTrabajos(@RequestParam Long idTrabajo,@RequestParam Long idTipoTrabajo, @RequestParam String fecha, @RequestParam Long idCliente,
            @RequestParam String numPlaca, @RequestParam Integer cantProductos, @RequestParam Long idEmpleado) {
        
        trabajosService.actualizarTrabajo(idTrabajo, idTipoTrabajo, fecha, idCliente, numPlaca, cantProductos, idEmpleado);
        return "redirect:/trabajos/listado";
    }
    @GetMapping("/eliminar/{id}")
    public String eliminarProducto(@PathVariable Long id) {
        trabajosService.eliminarTrabajo(id);
         return "redirect:/trabajos/listado";
    }
    
    
     @GetMapping("/agregar")
    public String agregar(Model model) {
           List<Empleados> empleados = empleadosService.getEmpleados();
        model.addAttribute("empleados", empleados);
          List<TipoTrabajo> tipoTrabajolista = tipoTrabajoService.getTiposTrabajos();
        model.addAttribute("tipoTrabajolista", tipoTrabajolista);
         List<Vehiculo> vehiculos = vehiculoService.getTiposVehiculos();
        model.addAttribute("vehiculos", vehiculos);
       Long id = trabajosService.obtenerUltimoTrabajo();
       model.addAttribute("idUltimoTrabajo", id);
       List<Cliente> clientes = clienteService.getClientes();
        model.addAttribute("clientes", clientes);
        return "trabajos/agregar";
    }
    @PostMapping("/guardar")
    public String guardar(@RequestParam Long idTrabajo,@RequestParam Long idTipoTrabajo, @RequestParam String fecha, @RequestParam Long idCliente,
            @RequestParam String numPlaca, @RequestParam Integer cantProductos, @RequestParam Long idEmpleado) {
        trabajosService.insertarTrabajo(idTrabajo, idTipoTrabajo, fecha, idCliente, numPlaca, cantProductos, idEmpleado);
        return "redirect:/trabajos/listado";
    }
    
}
