package com.cja.acsystem.repositories;

import com.cja.acsystem.entities.Accion;
import org.springframework.data.jpa.repository.JpaRepository;


import java.util.List;

public interface AccionRepository extends JpaRepository<Accion, Long> {
    public List<Accion> findByUnidadNegocioId(Long unidadNegocioId);
}
