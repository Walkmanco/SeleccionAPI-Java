package com.example.seleccion.dtos;

import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

import com.example.seleccion.entities.Equipo;
import com.example.seleccion.entities.Resultado;

@Component
@Mapper(componentModel = "spring")

public interface Mapeador {
    EquipoDto equipoToEquipoDto(Equipo equipo);
    Equipo equipoDtoToEquipo(EquipoDto equipoDto);
    ResultadoDto resultadoToResultadoDto(Resultado resultado);
    Resultado resultadoDtoTResultado(ResultadoDto resultadoDto);
}

