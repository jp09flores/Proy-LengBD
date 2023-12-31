package com.Proyecto.Controller;

import com.Proyecto.dao.vClientesDao;
import com.Proyecto.domain.Cliente;
import com.Proyecto.domain.Cursores;
import com.Proyecto.domain.vClientes;
import com.Proyecto.domain.vVehiculos;
import com.Proyecto.service.ClienteService;
import java.math.BigDecimal;
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
@RequestMapping("/clientes")
@Slf4j
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    @Autowired
    private vClientesDao vclientesdao;

    @GetMapping("/listado")
    public String listado(Model model) {
        List<Cliente> clientes = clienteService.getClientes();
        model.addAttribute("clientes", clientes);
        BigDecimal totalClientes = clienteService.obtenerTotalClientes();
        model.addAttribute("totalClientes", totalClientes);
        String clienteMasValorado = clienteService.obtenerClienteMasValorado();
        model.addAttribute("clienteMasValorado", clienteMasValorado);

        List<vClientes> vClientes = vclientesdao.findAll();
        model.addAttribute("vClientes", vClientes);
        return "clientes/listado";
    }

    @GetMapping("/modificar/{id}")
    public String obtenerCliente(@PathVariable Long id, Model model) {
        Cliente cliente = clienteService.seleccionarCliente(id);
        Long cliente_id = id;
        model.addAttribute("cliente", cliente);
        model.addAttribute("cliente_id", cliente_id);
        return "clientes/modificar";
    }

    @PostMapping("/actualizar")
    public String actualizarCliente(@RequestParam Long idCliente,
            @RequestParam String username,
            @RequestParam String contra,
            @RequestParam String nombre,
            @RequestParam String apellido,
            @RequestParam String direccion,
            @RequestParam String numTelefono,
            @RequestParam String correoElectronico) {
        clienteService.actualizarCliente(idCliente, username, contra, nombre, apellido, direccion, numTelefono, correoElectronico);
        return "redirect:/clientes/listado";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminarCliente(@PathVariable Long id) {
        clienteService.eliminarCliente(id);
        return "redirect:/clientes/listado";
    }

    @GetMapping("/agregar")
    public String agregar(Model model) {
        Long id = clienteService.obtenerUltimoCliente();
        model.addAttribute("idUltimoCliente", id);
        return "clientes/agregar";
    }

    @PostMapping("/guardar")
    public String guardar(@RequestParam Long idCliente, @RequestParam String username, @RequestParam String contra,
            @RequestParam String nombre, @RequestParam String apellido, @RequestParam String direccion,
            @RequestParam String telefono, @RequestParam String correo) {
        clienteService.insertarCliente(idCliente, username, contra, nombre, apellido, direccion, telefono, correo);
        return "redirect:/clientes/listado";
    }

    @GetMapping("/cursor/{username}")
    public String obtenerProveedorCursor(@PathVariable String username, Model model) {
        Cursores cursores = clienteService.Cursor(username);
        model.addAttribute("cursor", cursores.getOutput());
        return "clientes/cursor";
    }
}
