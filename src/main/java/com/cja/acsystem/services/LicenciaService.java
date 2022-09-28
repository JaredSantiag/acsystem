package com.cja.acsystem.services;

import java.util.List;

import com.cja.acsystem.dto.LicenciaDTO;

public interface LicenciaService {
	
	public LicenciaDTO crearLicencia(LicenciaDTO licenciaDTO);
	
	public List<LicenciaDTO> obtenerLicencias();
	
	public LicenciaDTO obtenerLicenciaPorId(Long id);
	
}
