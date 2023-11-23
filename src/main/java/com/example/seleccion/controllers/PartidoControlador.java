package com.example.seleccion.controllers;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import com.example.seleccion.dtos.PartidoDto;
import com.example.seleccion.dtos.PartidoDtoFront;
import com.example.seleccion.entities.Equipo;
import com.example.seleccion.entities.Partido;
import com.example.seleccion.services.PartidoServicio;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/v1")
public class PartidoControlador {
    private final PartidoServicio partidoServicio;
    private final ModelMapper mapper;

    public PartidoControlador(PartidoServicio partidoServicio,@Qualifier("DefaultMapper")ModelMapper mapper){
        this.partidoServicio=partidoServicio;
        this.mapper = mapper;
    }

    //Crear
    @PostMapping("/partidos/")
    public ResponseEntity<PartidoDtoFront> crearPartido(@RequestBody PartidoDto partido){
            
        Partido partidoCopia = partidoServicio.crearPartido(partido);

        return new ResponseEntity<>(this.partidoToPartidoDtoFront(partidoCopia),HttpStatus.CREATED);
    }

    //mostrar todos
    @GetMapping("/partidos/")
    public ResponseEntity<List<PartidoDtoFront>> mostrarTodos(){
        List<Partido> Lpartidos = partidoServicio.mostrarPartidos();
        List<PartidoDtoFront> partidoDtos = null;
        
        partidoDtos = Lpartidos.stream().map(this::partidoToPartidoDtoFront).toList();

        return ResponseEntity.ok().body(partidoDtos);
    }

    //buscar por id
    @GetMapping("/partidos/{id}")
    public ResponseEntity<PartidoDtoFront> buscarPorId(@PathVariable @Valid Long id){
        
        Optional<Partido> partido = partidoServicio.buscarPartidoPorId(id);
        PartidoDtoFront partidoDto = partidoToPartidoDtoFront(partido.get());        

        return ResponseEntity.ok().body(partidoDto);
    }
    
    //borrar todos
    @DeleteMapping("/partidos/")
    public ResponseEntity<Void> borrarTobdos(){
        partidoServicio.borrarTodosPartidos();

        return ResponseEntity.noContent().build();
    }
    

    //Borrar por id
    @DeleteMapping("/partidos/delete/{id}")
    public ResponseEntity<Equipo> deleteEquipo(@PathVariable("id") Long id){
        partidoServicio.borrarPartidoPorId(id);

        return ResponseEntity.noContent().build();
    }
    
    public Partido partidoDtoTPartido(PartidoDto partidoDto){
        return mapper.map(partidoDto, Partido.class);
    }

    public PartidoDto partidoToPartidoDto(Partido partido){
        return mapper.map(partido, PartidoDto.class);
    }

    public Partido partidoDtoFrontToPartido(PartidoDtoFront partidoDto){
        return mapper.map(partidoDto, Partido.class);
    }

    public PartidoDtoFront partidoToPartidoDtoFront(Partido partido){
        return mapper.map(partido, PartidoDtoFront.class);
    }


    
    
    

}
