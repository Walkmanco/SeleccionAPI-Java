package com.example.seleccion.repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


import com.example.seleccion.entities.Resultado;


@Repository
public interface ResultadoRepositorio extends JpaRepository<Resultado, Long> {
    
    @Query("SELECT r FROM Resultado r WHERE r.partido.id = :idPartido")
    Resultado buscarResultadoPorIdPartido(@Param("idPartido") Long idPartido); 
}
