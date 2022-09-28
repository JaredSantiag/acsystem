package com.cja.acsystem.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.cja.acsystem.repositories.FirmaRepository;
import com.cja.acsystem.dto.FirmaDTO;
import com.cja.acsystem.entities.Firma;
import com.cja.acsystem.exceptions.ResourceNotFoundException;

@Service
public class FirmaServiceImpl implements FirmaService{

	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private FirmaRepository firmaRepository;

	@Override
	public FirmaDTO crearFirma(FirmaDTO firmaDTO) {
		Firma firma = mapearEntidad(firmaDTO);

		Firma nuevaFirma = firmaRepository.save(firma);

		return mapearDTO(nuevaFirma);
	}

	@Override
	public List<FirmaDTO> obtenerFirmas() {
		List<Firma> firmas = firmaRepository.findAll();
		return firmas.stream().map(firma -> mapearDTO(firma)).collect(Collectors.toList());
	}

	@Override
	public FirmaDTO obtenerFirmaPorId(Long id) {
		Firma firma = firmaRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Firma", "id", id));
		return mapearDTO(firma);
	}

	@Override
	public FirmaDTO actualizarFirma(FirmaDTO firmaDTO, long id) {
		Firma firma = firmaRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Firma", "id", id));
		
		firma.setDiasPromesa(firmaDTO.getDiasPromesa());
		firma.setActivo(firmaDTO.isActivo());
		firma.setNombre(firmaDTO.getNombre());

		Firma firmaActualizada = firmaRepository.save(firma);
		
		return mapearDTO(firmaActualizada);		
	}

	@Override
	public void eliminarFirma(Long id) {
		Firma firma = firmaRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Firma", "id", id));
		firmaRepository.delete(firma);
		
	}
	
	// Convierte de Entidad a DTO
	private FirmaDTO mapearDTO(Firma firma) {
		FirmaDTO firmaDTO = modelMapper.map(firma, FirmaDTO.class);
		return firmaDTO;
	}

	// Convierte de DTO a Entidad
	private Firma mapearEntidad(FirmaDTO firmaDTO) {
		Firma firma = modelMapper.map(firmaDTO, Firma.class);
		return firma;
	}
	
}
