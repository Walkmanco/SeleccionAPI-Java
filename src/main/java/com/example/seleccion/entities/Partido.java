package com.example.seleccion.entities;


import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import com.example.seleccion.dtos.PartidoDto;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "partidos")
@NoArgsConstructor
@AllArgsConstructor
@Data

public class    Partido {
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotBlank(message = "Debe especificar nombre del estadio")
    private String estadio;
    private String fecha;
    
    
    @ManyToMany
    @JoinTable(name = "equipo_partido",  joinColumns = @JoinColumn(name = "partido_id",referencedColumnName = "id"), 
        inverseJoinColumns = @JoinColumn(name= "equipo_id", referencedColumnName = "id"))
    private List<Equipo> equipos = new ArrayList<>(); 

    @JsonIgnore
    @OneToOne(mappedBy = "partido",optional = true,cascade = CascadeType.REMOVE)
    private Resultado resultado;

    private String arbitroPrincipal;

    public Partido actualizarCon(PartidoDto partido) {
        return new  Partido(this.id, this.estadio, this.fecha, this.equipos, this.resultado,this.arbitroPrincipal);
    }

  
}
