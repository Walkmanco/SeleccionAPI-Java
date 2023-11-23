package com.example.seleccion.services;

import java.util.List;
import com.example.seleccion.entities.Equipo;

public interface EquipoServicio {    
    Equipo buscarPorId(Long id);
    List<Equipo> mostrarTodos();
    Equipo buscarPorNombre(String nombre);
    void borrarEquipo(Long id);    
    Equipo crearEquipo(Equipo equipo);
    Equipo actualizarEquipo(Long id, Equipo equipo);
    void borrarTodo();
}
