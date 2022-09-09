package com.cja.acsystem.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cja.acsystem.entities.UnidadNegocio;

public interface UnidadNegocioRepository extends JpaRepository<UnidadNegocio, Long>{
	public List<UnidadNegocio> findByFirmaId(Long litigionId);
}
