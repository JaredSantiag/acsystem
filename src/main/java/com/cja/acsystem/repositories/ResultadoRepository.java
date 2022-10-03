package com.cja.acsystem.repositories;

import com.cja.acsystem.entities.Resultado;
import org.springframework.data.jpa.repository.JpaRepository;

import com.cja.acsystem.entities.Firma;

import java.util.List;

public interface ResultadoRepository extends JpaRepository<Resultado, Long> {

    List<Resultado> findByAccionId(long accionId);
}
