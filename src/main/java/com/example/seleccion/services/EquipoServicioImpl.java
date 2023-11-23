package com.example.seleccion.services;

import java.util.List;
import java.util.Optional;



import org.springframework.stereotype.Service;


import com.example.seleccion.entities.Equipo;
import com.example.seleccion.exceptions.EquipoNoEncontradoException;
import com.example.seleccion.repositories.EquipoRepositorio;

@Service
public class EquipoServicioImpl implements EquipoServicio {

    private final EquipoRepositorio equipoRepositorio;

    public EquipoServicioImpl(EquipoRepositorio equipoRepositorio){
        this.equipoRepositorio = equipoRepositorio;
    }

    @Override
    public Equipo actualizarEquipo(Long id, Equipo nuevoEquipo) {
        Optional<Equipo> equipoOptional = equipoRepositorio.findById(id);   
        
            
        if (equipoOptional.isPresent()) {
            Equipo equipoInDB = equipoOptional.get();
        
            Equipo equipoActualizado = equipoInDB.actualizarCon(nuevoEquipo);
            System.out.println(equipoActualizado);
            System.out.println(nuevoEquipo);
            return equipoRepositorio.save(equipoActualizado);
                
        } else {
            throw new EquipoNoEncontradoException("No se encontró equipo con ID: " + id);        
        }
    }

    @Override
    public List<Equipo> mostrarTodos() {    
        return equipoRepositorio.findAll();
    }

    @Override
    public void borrarEquipo(Long id) {        
        
        if(!equipoRepositorio.findById(id).isPresent())
            throw new EquipoNoEncontradoException("no se ha encontrado equipo con ID: "+id);
        else
            equipoRepositorio.deleteById(id);
    }



    @Override
    public Equipo buscarPorId(Long id) {        
        Optional<Equipo> equipo = equipoRepositorio.findById(id);
        if(equipo.isPresent()) 
            return equipo.get();
        throw new EquipoNoEncontradoException("No se encontró equipo con id: "+ id);
    }

    @Override
    public Equipo buscarPorNombre(String nombre) {                
        if(equipoRepositorio.buscarPorNombre(nombre) == null || nombre.isEmpty())
            throw new EquipoNoEncontradoException("no se encontró equipo con nomnbre "+ nombre);
        return equipoRepositorio.buscarPorNombre(nombre);
        
    }

    @Override
    public Equipo crearEquipo(Equipo equipo) {        

        Equipo equipoCopia = new Equipo(equipo.getId()
                                    ,equipo.getNombre()
                                    ,equipo.getBandera()
                                    ,equipo.getDirectorTecnico()
                                    ,equipoRepositorio.buscarIdPartidos(equipo.getId())
                                    );

        return equipoRepositorio.save(equipoCopia);
    }

    @Override
    public void borrarTodo() {
        equipoRepositorio.deleteAll();       
    }

    
    
}
