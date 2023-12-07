
package com.Proyecto.service;
import com.Proyecto.domain.Empleados;
import java.util.List;

public interface EmpleadosService {
public List<Empleados> getEmpleados(); 

  

    public Empleados SeleccionarEmpleado(Long idEmpleado);

    public int eliminarEmpleado(Long idEmpleado);

    public void actualizarEmpleado(Long IdEmpleado, String nombre,
            String apellido,
            String direccion,
            String numTelefono,
            String correo,
            String salario,
            String FechaIngre,
            String FechaNac);

    public void insertarEmpleado(Long IdEmpleado, String nombre,
            String apellido,
            String direccion,
            String numTelefono,
            String correo,
            String salario,
            String FechaIngre,
            String FechaNac);

    public Long ObtenerUltimoEmpleado();
        
        
}
