package com.example.seleccion.entities;


import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.PositiveOrZero;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name="equipos")


public class Equipo {
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    @PositiveOrZero
    private Long id;
    
    @NotBlank(message = "El nombre no puede estar en blanco")    
    private String nombre;
    private String bandera;
    private String directorTecnico;

    @JsonIgnore
    @ManyToMany(mappedBy = "equipos")   
    private List<Partido> partidos = new ArrayList<>();

    public Equipo actualizarCon(Equipo equipo) {
        return new  Equipo(this.id, equipo.nombre, equipo.bandera, equipo.directorTecnico, equipo.partidos);
    }

    
}
