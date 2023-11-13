
package com.Proyecto.Controller;

import com.Proyecto.domain.TipoTrabajo;
import com.Proyecto.service.TipoTrabajoService;
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
@RequestMapping("/tipo")
@Slf4j
public class TIpoTrabajoController {
    
    @Autowired
    private TipoTrabajoService tipoTrabajoService;
    
    @GetMapping("/listado")
    public String listado(Model model) {
        List<TipoTrabajo> tipoTrabajos = tipoTrabajoService.getTiposTrabajos();
        model.addAttribute("tipo_trabajos", tipoTrabajos);
        return "tipo/listado";
    }
    
    @GetMapping("/modificar/{id}")
    public String obtenerTipoTrabajo(@PathVariable Long id, Model model) {
        TipoTrabajo tipoTrabajo = tipoTrabajoService.seleccionarTipoTrabajo(id);
        Long tipo_Trabajo_id = id;
        model.addAttribute("tipoTrabajo", tipoTrabajo);
        model.addAttribute("tipo_Trabajo_id", tipo_Trabajo_id);
        return "tipo/modificar";
    }
    
     @PostMapping("/actualizar")
    public String actualizarTipoTrabajo(@RequestParam Long id, @RequestParam String nombre, @RequestParam String requisitos) {
        tipoTrabajoService.actualizarTipoTrabajo(id, nombre, requisitos);
        return "redirect:/tipo/listado";
    }
    @GetMapping("/eliminar/{id}")
    public String eliminarTipoTrabajo(@PathVariable Long id) {
        tipoTrabajoService.eliminarTipoTrabajo(id);
         return "redirect:/tipo/listado";
    }
    
    @GetMapping("/agregar")
    public String agregar(Model model) {
       Long id = tipoTrabajoService.obtenerUltimoTipoTrabajo();
       model.addAttribute("idUltimoUsuario", id);
        return "tipo/agregar";
    }
    @PostMapping("/guardar")
    public String guardar(@RequestParam Long id, @RequestParam String nombre, @RequestParam String requisitos, @RequestParam String contenido, @RequestParam String detalles) {
        tipoTrabajoService.insertarTipoTrabajo(id, nombre, requisitos, contenido, detalles);
        return "redirect:/tipo/listado";
    }
}