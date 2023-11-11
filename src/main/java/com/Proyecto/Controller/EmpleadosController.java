/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/springframework/Controller.java to edit this template
 */
package com.Proyecto.Controller;
import com.Proyecto.domain.Empleados;
import com.Proyecto.service.EmpleadosService;
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
@RequestMapping("/empleados")
@Slf4j
public class EmpleadosController {

    @Autowired
    private EmpleadosService empleadosService;

    @GetMapping("/listado")
    public String listado(Model model) {
        List<Empleados> empleados = empleadosService.getEmpleados();
        model.addAttribute("empleados", empleados);
        return "empleados/listado";
    }
    
    @GetMapping("/modificar/{IdEmpleado}")
    public String obtenerEmpleado(@PathVariable Long IdEmpleado, Model model) {
        Empleados empleados = empleadosService.SeleccionarEmpleado(IdEmpleado);
        Long ID_EMPLEADO = IdEmpleado;
        model.addAttribute("empleado", empleados);
        model.addAttribute("IdEmpleado", ID_EMPLEADO);
        return "empleados/modificar";
    }
    @PostMapping("/actualizar")
    public String actualizarEmpleado(@RequestParam Long IdEmpleado, @RequestParam String nombre,@RequestParam String apellido,@RequestParam String direccion, @RequestParam String numTelefono,@RequestParam String correo,@RequestParam String salario,@RequestParam String FechaIngre,@RequestParam String FechaNac) {
        empleadosService.actualizarEmpleado(IdEmpleado, nombre,apellido,direccion,numTelefono,correo,salario,FechaIngre,FechaNac);
        return "redirect:/empleados/listado";
    }
    @GetMapping("/eliminar/{IdEmpleado}")
    public String eliminarProveedor(@PathVariable Long IdEmpleado) {
        empleadosService.eliminarEmpleado(IdEmpleado);
         return "redirect:/empleados/listado";
    }
    @GetMapping("/agregar")
    public String agregar(Model model) {
       Long id = empleadosService.ObtenerUltimoEmpleado();
       model.addAttribute("idUltimoEmpleado", id);
        return "empleados/agregar";
    }
    @PostMapping("/guardar")
    public String guardar(@RequestParam Long IdEmpleado, @RequestParam String nombre,@RequestParam String apellido,@RequestParam String direccion, @RequestParam String numTelefono,@RequestParam String correo,@RequestParam String salario,@RequestParam String FechaIngre,@RequestParam String FechaNac) {
        empleadosService.insertarEmpleado(IdEmpleado, nombre,apellido,direccion,numTelefono,correo,salario,FechaIngre,FechaNac);
        return "redirect:/empleados/listado";
    }
}