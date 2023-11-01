package com.example.data.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.data.model.Cita;

@Repository
public interface CitaRepository  extends CrudRepository<Cita, Long>{

}
