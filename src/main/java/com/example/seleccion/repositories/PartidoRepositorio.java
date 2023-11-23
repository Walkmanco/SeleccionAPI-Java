package com.example.seleccion.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

import com.example.seleccion.entities.Partido;

@Repository
public interface PartidoRepositorio extends JpaRepository<Partido,Long>{    

}
