package com.example.seleccion.dtos;

import java.util.List;
import com.example.seleccion.entities.Equipo;
import lombok.Data;

@Data

public class PartidoDtoFront {

    private Long id;
    private String estadio;
    private String fecha;
    private List<Equipo> equipos;
    private String arbitroPrincipal;

}


