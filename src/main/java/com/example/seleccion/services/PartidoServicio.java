package com.example.seleccion.services;

import java.util.List;
import java.util.Optional;

import com.example.seleccion.dtos.PartidoDto;
import com.example.seleccion.entities.Partido;

public interface PartidoServicio {

    List<Partido> mostrarPartidos();
    Partido crearPartido(PartidoDto partido);
    Optional<Partido> buscarPartidoPorId(Long id);
    Optional<Partido> actualizarPartido(Long id, PartidoDto partido);
    void borrarPartidoPorId(Long id);
    void borrarTodosPartidos();
}
