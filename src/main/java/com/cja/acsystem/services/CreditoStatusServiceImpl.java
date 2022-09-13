package com.cja.acsystem.services;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cja.acsystem.dto.CreditoStatusDTO;
import com.cja.acsystem.entities.CreditoStatus;
import com.cja.acsystem.exceptions.ResourceNotFoundException;
import com.cja.acsystem.repositories.CreditoStatusRepository;

@Service
public class CreditoStatusServiceImpl implements CreditoStatusService {

	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private CreditoStatusRepository creditoStatusRepository;

	@Override
	public CreditoStatusDTO crearCreditoStatus(CreditoStatusDTO creditoStatusDTO) {
		CreditoStatus creditoStatus = mapearEntidad(creditoStatusDTO);
		CreditoStatus nuevoCreditoStatus = creditoStatusRepository.save(creditoStatus);
		return mapearDTO(nuevoCreditoStatus);
	}

	@Override
	public List<CreditoStatusDTO> obtenerCreditosStatus() {
		List<CreditoStatus> creditosStatus = creditoStatusRepository.findAll();
		return creditosStatus.stream().map(creditoStatus -> mapearDTO(creditoStatus)).collect(Collectors.toList());
	}

	@Override
	public CreditoStatusDTO obtenerCreditoStatusPorId(Long id) {
		CreditoStatus creditoStatus = creditoStatusRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Credito Estatus", "id", id));
		return mapearDTO(creditoStatus);
	}

	@Override
	public CreditoStatusDTO actualizarCreditoStatus(Long id, CreditoStatusDTO creditoStatusDTO) {
		CreditoStatus creditoStatus = creditoStatusRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Credito Estatus", "id", id));
		creditoStatus.setNombre(creditoStatusDTO.getNombre());
		creditoStatus.setActivo(creditoStatusDTO.isActivo());
		CreditoStatus nuevoCreditoStatus = creditoStatusRepository.save(creditoStatus);
		return mapearDTO(nuevoCreditoStatus);
	}

	@Override
	public void eliminarCreditoStatus(Long id) {
		CreditoStatus creditoStatus = creditoStatusRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Credito Estatus", "id", id));
		creditoStatusRepository.delete(creditoStatus);
	}

	// Convierte de Entidad a DTO
	private CreditoStatusDTO mapearDTO(CreditoStatus creditoStatus) {
		CreditoStatusDTO creditoStatusDTO = modelMapper.map(creditoStatus, CreditoStatusDTO.class);
		return creditoStatusDTO;
	}

	// Convierte de DTO a Entidad
	private CreditoStatus mapearEntidad(CreditoStatusDTO creditoStatusDTO) {
		CreditoStatus creditoStatus = modelMapper.map(creditoStatusDTO, CreditoStatus.class);
		return creditoStatus;
	}

}
