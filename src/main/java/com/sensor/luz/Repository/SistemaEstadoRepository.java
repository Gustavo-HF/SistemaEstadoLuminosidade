package com.sensor.luz.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sensor.luz.Model.SistemaEstado;

@Repository
public interface  SistemaEstadoRepository extends JpaRepository<SistemaEstado, Long> {

    
}
