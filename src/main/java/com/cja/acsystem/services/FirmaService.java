package com.cja.acsystem.services;

import java.util.List;

import com.cja.acsystem.dto.FirmaDTO;

public interface FirmaService {

	public FirmaDTO crearFirma(FirmaDTO firmaDTO);
	
	public List<FirmaDTO> obtenerFirmas();
	
	public FirmaDTO obtenerFirmaPorId(Long id);
	
	public FirmaDTO actualizarFirma(FirmaDTO FirmaDTO, long id);
	
	public void eliminarFirma(Long id);
}
