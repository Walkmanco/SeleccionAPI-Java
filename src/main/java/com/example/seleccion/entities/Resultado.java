package com.example.seleccion.entities;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.example.seleccion.dtos.ResultadoDto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "resultados")
@AllArgsConstructor
@NoArgsConstructor
@Data


public class Resultado {
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private int golVisitante;
    private int golLocal;
    private int noTarjetaRoja;
    private int noTarjetaAmarilla;
    

    @OneToOne
    @JoinColumn(name = "resultado", unique = true)  
    private Partido partido;

    public Resultado actualizarCon(ResultadoDto resultado) {
        return new  Resultado(this.id,this.golVisitante,this.golLocal,this.noTarjetaRoja,this.noTarjetaRoja,this.partido);
    }

    public Resultado(Long id, int golVisitante, int golLocal, int noTarjetaRoja, int noTarjetaAmarilla) {
        this.id = id;
        this.golVisitante = golVisitante;
        this.golLocal = golLocal;
        this.noTarjetaRoja = noTarjetaRoja;
        this.noTarjetaAmarilla = noTarjetaAmarilla;
    }


}
