package com.example.seleccion.controllers;



import java.util.List;
import java.util.Optional;


import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Qualifier;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import com.example.seleccion.dtos.ResultadoDto;
import com.example.seleccion.dtos.ResultadoDtoFront;
import com.example.seleccion.entities.Resultado;
import com.example.seleccion.services.ResultadoServicio;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/v1")

public class ResultadoControlador {
    
    private final ResultadoServicio resultadoServicio;
    private final ModelMapper mapper;
    
    public ResultadoControlador(ResultadoServicio resultadoServicio,@Qualifier("DefaultMapper") ModelMapper mapper){
        this.resultadoServicio=resultadoServicio;
        this.mapper = mapper;
    }

     //Crear
    @PostMapping("/resultados")
    public ResponseEntity<ResultadoDtoFront> crear(@RequestBody ResultadoDto resultado){        
        
        Resultado resultadoCreado = resultadoServicio.crearResultado(resultado);

       return new ResponseEntity<>(this.resultadoToResultadoDtoFront(resultadoCreado),HttpStatus.CREATED);
    }

    //Actualizar
    
    @PutMapping("/resultados/actualizar/{id}")
    public ResponseEntity<ResultadoDtoFront> actualizarResultado(@PathVariable Long id,@RequestBody ResultadoDto resultadoActualizado){
        Optional<Resultado> resultado = resultadoServicio.actualizarResultado(id, resultadoActualizado);
        ResultadoDtoFront resultadoDto = resultadoToResultadoDtoFront(resultado.get());
        
        return ResponseEntity.ok().body(resultadoDto);
    }

    //mostrar
    @GetMapping("/resultados")
    public ResponseEntity<List<Resultado>> mostrarTodos(){
        List<Resultado> Lresultado = resultadoServicio.mostrarTodos();

        return ResponseEntity.ok().body(Lresultado);
    }

    //buscar por id
    @GetMapping("/resultados/{id}")
    public ResponseEntity<Optional<Resultado>> buscarPorId(@PathVariable @Valid Long id){     
        Optional<Resultado> resultadOptional = resultadoServicio.buscarPorId(id);

        return ResponseEntity.ok().body(resultadOptional);
    } 

    //borrar todo
    @DeleteMapping("/resultados")
    public ResponseEntity<Void> borrarTodo(){
        resultadoServicio.borrarTodo();

        return ResponseEntity.noContent().build();
    }

    //Borrar por id
    @DeleteMapping("/resultados/deleteById/{id}")
    public ResponseEntity<Resultado> deleteEquipo(@PathVariable("id") Long id){
        resultadoServicio.borarrPorId(id);            

        return ResponseEntity.noContent().build();
    }



   public Resultado resultadoDtoTResultado(ResultadoDto resultadoDto){
        return mapper.map(resultadoDto, Resultado.class);
    }

    public ResultadoDto resultadoToResultadoDto(Resultado resultado){
        return mapper.map(resultado, ResultadoDto.class);
    }

    public Resultado resultadoDtoFrontToResultado(ResultadoDtoFront resultadoDtoFront){
        return mapper.map(resultadoDtoFront, Resultado.class);
    }

    public ResultadoDtoFront resultadoToResultadoDtoFront(Resultado resultado){
        return mapper.map(resultado, ResultadoDtoFront.class);
    }

}
