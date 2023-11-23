package com.example.seleccion.dtos;






import lombok.Data;

@Data
public class ResultadoDto {
    
    private Long id;
    private int golVisitante;
    private int golLocal;
    private int noTarjetaRoja;
    private int noTarjetaAmarilla;
    private Long idPartido;
}
