package com.cja.acsystem.repositories;

import com.cja.acsystem.entities.Accion;
import com.cja.acsystem.entities.UnidadNegocio;
import org.springframework.data.jpa.repository.JpaRepository;

import com.cja.acsystem.entities.Firma;

import java.util.List;

public interface AccionRepository extends JpaRepository<Accion, Long> {
    public List<Accion> findByFirmaId(Long litigionId);
}
