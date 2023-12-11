package com.Proyecto.Controller;

import com.Proyecto.dao.vVehiculoDao;
import com.Proyecto.domain.Cliente;
import com.Proyecto.domain.Cursores;
import com.Proyecto.domain.Vehiculo;
import com.Proyecto.domain.vVehiculos;
import com.Proyecto.service.ClienteService;
import com.Proyecto.service.VehiculoService;
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

@Controller
@RequestMapping("/vehiculo")
@Slf4j
public class VehiculoController {

    @Autowired
    private VehiculoService VehiculoService;

    @Autowired
    private vVehiculoDao vVehiculoDao;
    
     @Autowired
    private ClienteService clienteService;

    @GetMapping("/listado")
    public String listado(Model model) {
        List<Vehiculo> vehiculos = VehiculoService.getTiposVehiculos();
        model.addAttribute("vehiculos", vehiculos);
        List<vVehiculos> vVehiculos = vVehiculoDao.findAll();
        model.addAttribute("vVehiculos", vVehiculos);
        return "vehiculo/listado";
    }

    @GetMapping("/modificar/{placa}")
    public String obtenerVehiculo(@PathVariable String placa, Model model) {
        Vehiculo vehiculo = VehiculoService.seleccionarVehiculo(placa);
        String vehiculo_id = placa;
        model.addAttribute("vehiculo", vehiculo);
        model.addAttribute("vehiculo_id", vehiculo_id);
        List<Cliente> clientes = clienteService.getClientes();
        model.addAttribute("clientes", clientes);
        return "vehiculo/modificar";
    }

    @PostMapping("/actualizar")
    public String actualizarVehiculo(@RequestParam String numPlaca, @RequestParam String numMotor,@RequestParam Long idCliente, @RequestParam String marca, @RequestParam String color, @RequestParam String modelo, @RequestParam String year) {
        VehiculoService.actualizarVehiculo(numPlaca, numMotor,idCliente, marca, color, modelo, year);
        return "redirect:/vehiculo/listado";
    }

    @GetMapping("/eliminar/{placa}")
    public String eliminarVehiculo(@PathVariable String placa) {
        VehiculoService.eliminarVehiculo(placa);
        return "redirect:/vehiculo/listado";
    }

    @GetMapping("/agregar")
    public String agregar(Model model) {
        List<Cliente> clientes = clienteService.getClientes();
        model.addAttribute("clientes", clientes);
        return "vehiculo/agregar";
    }

    @PostMapping("/guardar")
    public String guardar(@RequestParam String numPlaca, @RequestParam String numMotor,@RequestParam Long idCliente, @RequestParam String marca, @RequestParam String color, @RequestParam String modelo, @RequestParam String year) {
        VehiculoService.insertarVehiculo(numPlaca, numMotor, idCliente, marca, color, modelo, year);
        return "redirect:/vehiculo/listado";
    }

    @GetMapping("/cursor/{placa}")
    public String obtenerProveedorCursor(@PathVariable String placa, Model model) {
        Cursores cursores = VehiculoService.Cursor(placa.charAt(0));
        model.addAttribute("proveedorDetails", cursores.getOutput());
        return "proveedores/cursor";
    }

}
