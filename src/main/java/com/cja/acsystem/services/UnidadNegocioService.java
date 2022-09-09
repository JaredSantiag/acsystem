package com.cja.acsystem.services;

import java.util.List;

import com.cja.acsystem.dto.UnidadNegocioDTO;

public interface UnidadNegocioService {
	public UnidadNegocioDTO crearUnidadNegocio(long firmaId, UnidadNegocioDTO unidadNegocioDTO);
	
	public List<UnidadNegocioDTO> obtenerUnidadesPorFirmaId(long FirmaId);
	
	public UnidadNegocioDTO obtenerUnidadPorId(Long firmaId, Long unidadId);
	
	public UnidadNegocioDTO actualizarUnidadNegocio(Long firmaId, Long unidadId, UnidadNegocioDTO unidadNegocioDTO);
	
	public void eliminarUnidadNegocio(Long firmaId, Long unidadId);

}
