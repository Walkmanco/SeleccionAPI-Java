package com.example.seleccion.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;


import com.example.seleccion.dtos.PartidoDto;

import com.example.seleccion.entities.Equipo;
import com.example.seleccion.entities.Partido;
import com.example.seleccion.exceptions.EquipoNoEncontradoException;
import com.example.seleccion.repositories.PartidoRepositorio;


@Service
public class PartidoServicioImpl implements PartidoServicio{
    

    private final PartidoRepositorio partidoRespositorio;
    
    private final EquipoServicio equipoServicio;

    public PartidoServicioImpl(PartidoRepositorio partidoRespositorio, EquipoServicio equipoServicio){
        this.partidoRespositorio = partidoRespositorio;
        this.equipoServicio = equipoServicio;
        
    }

    @Override
    public Optional<Partido> actualizarPartido(Long id, PartidoDto partido) {        
        Optional<Partido> partidOptional = partidoRespositorio.findById(id);
        
        if(partidOptional.isPresent()){
            List<Equipo> lEquipos = new ArrayList<>();
            lEquipos.add(equipoServicio.buscarPorId(partido.getIdEquipos().get(0)));
            lEquipos.add(equipoServicio.buscarPorId(partido.getIdEquipos().get(1)));
            

            return partidoRespositorio.findById(id)
            .map(inDB -> {                
                inDB.setEstadio(partido.getEstadio());
                inDB.setFecha(partido.getFecha());    
                inDB.setEquipos(lEquipos);
                inDB.setArbitroPrincipal(partido.getArbitroPrincipal());                                                                    
  
            return partidoRespositorio.save(inDB);
            });
        }            
        else
            throw new EquipoNoEncontradoException("No se encontró resultado con id: "+ id);
    }

    @Override
    public void borrarPartidoPorId(Long id) {
        Optional<Partido> partidoOptional = partidoRespositorio.findById(id);
        
        if(partidoOptional.isPresent())             
            partidoRespositorio.deleteById(id);  
        else 
        throw new EquipoNoEncontradoException("No se encontró partido con id: "+ id);
        

    }

    @Override
    public Optional<Partido> buscarPartidoPorId(Long id){
        Optional<Partido> partidoOptional = partidoRespositorio.findById(id);
        
        if(partidoOptional.isPresent()) 
            return partidoRespositorio.findById(id);
        throw new EquipoNoEncontradoException("No se encontró partido con id: "+ id);
            
            
    }

    @Override
    public Partido crearPartido(PartidoDto partido) {        
        List<Equipo> lEquipos = new ArrayList<>();
        lEquipos.add(equipoServicio.buscarPorId(partido.getIdEquipos().get(0)));
        lEquipos.add(equipoServicio.buscarPorId(partido.getIdEquipos().get(1)));
        

        Partido partidoCopia = new Partido(partido.getId()
                                ,partido.getEstadio()
                                ,partido.getFecha()
                                ,lEquipos
                                ,null, partido.getArbitroPrincipal());
        return partidoRespositorio.save(partidoCopia);
    }

    @Override
    public List<Partido> mostrarPartidos() {        
        return partidoRespositorio.findAll();
    }

    @Override
    public void borrarTodosPartidos() {   
        partidoRespositorio.deleteAll();
    }
    
}
