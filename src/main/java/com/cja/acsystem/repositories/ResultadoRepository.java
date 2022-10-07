package com.cja.acsystem.repositories;

import com.cja.acsystem.entities.Accion;
import com.cja.acsystem.entities.Resultado;
import org.springframework.data.jpa.repository.JpaRepository;


import java.util.List;

public interface ResultadoRepository extends JpaRepository<Resultado, Long> {
    public List<Resultado> findByAccionId(Long accionId);
}
