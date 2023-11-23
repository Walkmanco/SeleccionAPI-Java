package com.example.seleccion.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.seleccion.dtos.ResultadoDto;
import com.example.seleccion.entities.Partido;
import com.example.seleccion.entities.Resultado;
import com.example.seleccion.exceptions.EquipoNoEncontradoException;
import com.example.seleccion.repositories.ResultadoRepositorio;

@Service

public class ResultadoServicioImpl implements ResultadoServicio {

    private final ResultadoRepositorio resultadoRepositorio;
    private final PartidoServicio partidoServicio;

    public ResultadoServicioImpl(ResultadoRepositorio resultadoRepositorio,PartidoServicio partidoServicio){
        this.resultadoRepositorio = resultadoRepositorio;
        this.partidoServicio = partidoServicio;
    }



    @Override
    public void borarrPorId(Long id) {
        Optional<Resultado> resultadOptional = resultadoRepositorio.findById(id);
        if(resultadOptional.isPresent()) 
            resultadoRepositorio.deleteById(id);
        else
            throw new EquipoNoEncontradoException("No se encontró resultado con id: "+ id);

        
        
    }

    @Override
    public Optional<Resultado> buscarPorId(Long id) {    
          Optional<Resultado> resultadOptional = resultadoRepositorio.findById(id);
        if(resultadOptional.isPresent()) 
            return resultadoRepositorio.findById(id);
        else
        throw new EquipoNoEncontradoException("No se encontró resultado con id: "+ id);
        
    }

    @Override
    public Resultado crearResultado(ResultadoDto resultado) {     
        Optional<Partido> partido = partidoServicio.buscarPartidoPorId(resultado.getIdPartido());

        Resultado resultadoCopia = new Resultado(resultado.getId()
                                    ,resultado.getGolVisitante()
                                    ,resultado.getGolLocal()
                                    ,resultado.getNoTarjetaRoja()
                                    ,resultado.getNoTarjetaAmarilla()
                                    ,partido.get());

        return resultadoRepositorio.save(resultadoCopia);
    }


    
    
    @Override
    public Optional<Resultado> actualizarResultado(Long id, ResultadoDto resultado) {
        Optional<Resultado> resultadOptional = resultadoRepositorio.findById(id);
        if(resultadOptional.isPresent()){
            return resultadoRepositorio.findById(id)
            .map(resultadoImDB -> {
            resultadoImDB.setGolLocal(resultado.getGolLocal());
            resultadoImDB.setGolVisitante(resultado.getGolVisitante());
            resultadoImDB.setNoTarjetaAmarilla(resultado.getNoTarjetaAmarilla());
            resultadoImDB.setNoTarjetaRoja(resultado.getNoTarjetaRoja());        
  
            return resultadoRepositorio.save(resultadoImDB);
        });
        }            
        else
            throw new EquipoNoEncontradoException("No se encontró resultado con id: "+ id);

    }



    @Override
    public List<Resultado> mostrarTodos() {
        return resultadoRepositorio.findAll();
    }



    @Override
    public void borrarTodo() {
        resultadoRepositorio.deleteAll();
    }


    
    

    
}
