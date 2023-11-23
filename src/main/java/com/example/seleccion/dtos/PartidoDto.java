package com.example.seleccion.dtos;






import java.util.List;



import lombok.Data;

@Data

public class PartidoDto {
    private Long id;
    private String estadio;
    private String fecha;
    private List<Long> idEquipos;
    private String arbitroPrincipal;

}
