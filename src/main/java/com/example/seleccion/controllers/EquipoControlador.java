package com.example.seleccion.controllers;


import java.net.URI;
import java.util.List;

import javax.validation.Valid;


import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.example.seleccion.dtos.EquipoDto;
import com.example.seleccion.entities.Equipo;
import com.example.seleccion.services.EquipoServicio;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/v1")
@Validated

public class EquipoControlador {
    
    private final EquipoServicio equipoServicio;    
    private final ModelMapper mapper;
    

    public EquipoControlador(EquipoServicio equipoServicio,@Qualifier("DefaultMapper") ModelMapper mapper){
        this.equipoServicio = equipoServicio;
        this.mapper = mapper;
    }


    //Mostrar
    @GetMapping("/equipos")
    public ResponseEntity<List<EquipoDto>> mostrarTodos(){
        List<Equipo> lEquipos = equipoServicio.mostrarTodos();
        List<EquipoDto> equipoDto = null;
        
        equipoDto = lEquipos.stream().map(this::equipoToEquipoDto).toList();

        return ResponseEntity.ok().body(equipoDto);
       
    }

    //buscar por id
    @GetMapping("/equipos/{id}")
    public ResponseEntity<EquipoDto> mostrarPorId(@PathVariable @Valid Long id){                                
        Equipo equipo = equipoServicio.buscarPorId(id); 
        EquipoDto equipoDto = equipoToEquipoDto(equipo);
        

        return ResponseEntity.ok().body(equipoDto);
    }

    //buscar por nombre
    @GetMapping("/equipos/buscarNombre/{nombre}")
    public ResponseEntity<EquipoDto> buscarPorNombre(@PathVariable(name = "nombre") String nombre){        
        Equipo equipo = equipoServicio.buscarPorNombre(nombre);
        EquipoDto equipoDto = equipoToEquipoDto(equipo);

        return ResponseEntity.ok().body(equipoDto);
    }

    //Crear
    @PostMapping("/equipos")
    public ResponseEntity<EquipoDto> crearEquipo(@RequestBody @Valid Equipo equipo){
        
        Equipo equipoCreado = equipoServicio.crearEquipo(equipo);
        EquipoDto equipoDto = equipoToEquipoDto(equipoCreado);

        URI rutaUri = ServletUriComponentsBuilder
                        .fromCurrentRequest()
                        .path("/id").buildAndExpand(equipoCreado.getId())
                        .toUri();

        return ResponseEntity.created(rutaUri).body(equipoDto);
    }
    
    //Actualizar
    @PutMapping("/equipos/actualizar/{id}")
    public ResponseEntity<EquipoDto> actualizarEquipo(@PathVariable Long id, @RequestBody Equipo equipoActualizado){
        Equipo equipo= equipoServicio.actualizarEquipo(id, equipoActualizado);
        EquipoDto equipoDto = equipoToEquipoDto(equipo);

        return ResponseEntity.ok().body(equipoDto);
    }

    //Borrar por id
    @DeleteMapping("/equipos/deleteById/{id}")
    public ResponseEntity<Equipo> deleteEquipo(@PathVariable("id") Long id){
        equipoServicio.borrarEquipo(id);

        return ResponseEntity.noContent().build();
    }
    //borrar todo
    @DeleteMapping("/equipos")
    public ResponseEntity<Void> borrarTodo(){
        equipoServicio.borrarTodo();

        return ResponseEntity.noContent().build();
    }

    public EquipoDto equipoToEquipoDto(Equipo equipo){
        return mapper.map(equipo, EquipoDto.class);
    }

    public Equipo equipoDtoToEquipo(EquipoDto equipoDto){
        return mapper.map(equipoDto, Equipo.class);
    }

}
