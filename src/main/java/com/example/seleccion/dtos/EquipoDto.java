package com.example.seleccion.dtos;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;

import lombok.Data;

@Data

public class EquipoDto {
    @PositiveOrZero
    private long id;
    @NotBlank(message = "El nombre no puede estar en blanco")
    @NotNull(message = "Debe especificar nombre")
    private String nombre;
    private String bandera;
    private String directorTecnico;

}
