package com.cja.acsystem.services;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cja.acsystem.dto.LicenciaDTO;
import com.cja.acsystem.entities.Licencia;
import com.cja.acsystem.exceptions.ResourceNotFoundException;
import com.cja.acsystem.repositories.LicenciaRepository;

@Service
public class LicenciaServiceImpl implements LicenciaService{

	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private LicenciaRepository licenciaRepository;

	@Override
	public LicenciaDTO crearLicencia(LicenciaDTO licenciaDTO) {
		Licencia licencia = mapearEntidad(licenciaDTO);
		Licencia nuevaLicencia = licenciaRepository.save(licencia);
		
		LicenciaDTO licenciaRespuesta = mapearDTO(nuevaLicencia);
		return licenciaRespuesta;
	}
	
	@Override
	public List<LicenciaDTO> obtenerLicencias() {
		List<Licencia> licencias = licenciaRepository.findAll();
		return licencias.stream().map(firma -> mapearDTO(firma)).collect(Collectors.toList());
	}

	@Override
	public LicenciaDTO obtenerLicenciaPorId(Long id) {
		Licencia licencia = licenciaRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Licencia", "id", id));
		return mapearDTO(licencia);
	}

	
	// Convierte Entidad a DTO
	private LicenciaDTO mapearDTO(Licencia licencia) {
		LicenciaDTO licenciaDTO = modelMapper.map(licencia, LicenciaDTO.class);
		return licenciaDTO;
	}

	// Convierte de DTO a Entidad
	private Licencia mapearEntidad(LicenciaDTO licenciaDTO) {
		Licencia licencia = modelMapper.map(licenciaDTO, Licencia.class);
		return licencia;
	}

}
