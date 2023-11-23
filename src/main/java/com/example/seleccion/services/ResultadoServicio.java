package com.example.seleccion.services;

import java.util.List;
import java.util.Optional;

import com.example.seleccion.dtos.ResultadoDto;
import com.example.seleccion.entities.Resultado;

public interface ResultadoServicio {
    Optional<Resultado> buscarPorId(Long id);
    void borarrPorId(Long id);
    List<Resultado> mostrarTodos();
    Resultado crearResultado(ResultadoDto resultado);
    void borrarTodo();
    //Resultado actualizarResultado(Long id, ResultadoDto resultado);
    Optional<Resultado> actualizarResultado(Long id, ResultadoDto resultado);
    
}
