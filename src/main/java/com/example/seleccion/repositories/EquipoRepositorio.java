package com.example.seleccion.repositories;







import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.seleccion.entities.Equipo;
import com.example.seleccion.entities.Partido;


@Repository
public interface EquipoRepositorio extends JpaRepository<Equipo,Long>{    
    
    @Query("SELECT e FROM Equipo e WHERE e.nombre=?1")
    Equipo buscarPorNombre(@Param("nombre") String nombre); 
    
    @Query("SELECT e FROM Equipo e WHERE e.id IN (:ids)")
    List<Equipo> buscarPorID(@Param("ids") List<Long> ids);

    //buscar partidos por ids
    @Query("SELECT p FROM Partido p WHERE p.id IN (:ids)")
    List<Partido> buscarIdPartidos(@Param("ids") Long ids);
    

}
