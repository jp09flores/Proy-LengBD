
package com.Proyecto.Controller;

import com.Proyecto.domain.Cliente;
import com.Proyecto.service.ClienteService;
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
@RequestMapping("/cliente")
@Slf4j
public class ClienteController {
    
    @Autowired
    private ClienteService clienteService;
    
    
    @GetMapping("/listado")
    public String listado(Model model) {
        List<Cliente> clientes = clienteService.getClientes();
        model.addAttribute("clientes", clientes);
        return "cliente/listado";
    }
    
    @GetMapping("/modificar/{id}")
    public String obtenerCliente(@PathVariable Long id, Model model) {
        Cliente cliente = clienteService.seleccionarCliente(id);
        Long cliente_id = id;
        model.addAttribute("cliente", cliente);
        model.addAttribute("cliente_id", cliente_id);
        return "cliente/modificar";
    }
    
    @PostMapping("/actualizar")
    public String actualizarCliente(@RequestParam Long id, @RequestParam String username, @RequestParam String correo) {
        clienteService.actualizarCliente(id, username, correo);
        return "redirect:/cliente/listado";
    }
    @GetMapping("/eliminar/{id}")
    public String eliminarCliente(@PathVariable Long id) {
        clienteService.eliminarCliente(id);
         return "redirect:/cliente/listado";
    }
    
    @GetMapping("/agregar")
    public String agregar(Model model) {
       Long id = clienteService.obtenerUltimoCliente();
       model.addAttribute("idUltimoUsuario", id);
        return "usuario/agregar";
    }
    @PostMapping("/guardar")
    public String guardar(@RequestParam Long id, @RequestParam String username, @RequestParam String contra, @RequestParam String nombre, @RequestParam String apellido,@RequestParam String direccion,@RequestParam String telefono, @RequestParam String correo) {
        clienteService.insertarCliente(id, username, contra, nombre, apellido, direccion, telefono, correo);
        return "redirect:/usuario/listado";
    }
}
