package com.cja.acsystem.services;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.cja.acsystem.dto.UnidadNegocioDTO;
import com.cja.acsystem.entities.Firma;
import com.cja.acsystem.entities.UnidadNegocio;
import com.cja.acsystem.exceptions.AcsystemAppException;
import com.cja.acsystem.exceptions.ResourceNotFoundException;
import com.cja.acsystem.repositories.FirmaRepository;
import com.cja.acsystem.repositories.UnidadNegocioRepository;

@Service
public class UnidadNegocioServiceImpl implements UnidadNegocioService {
	
	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private UnidadNegocioRepository unidadNegocioRepository;

	@Autowired
	private FirmaRepository firmaRepository;

	@Override
	public UnidadNegocioDTO crearUnidadNegocio(long firmaId, UnidadNegocioDTO unidadNegocioDTO) {
		UnidadNegocio unidadNegocio = mapearEntidad(unidadNegocioDTO);
		Firma firma = firmaRepository.findById(firmaId)
				.orElseThrow(() -> new ResourceNotFoundException("Firma", "id", firmaId));
		unidadNegocio.setFirma(firma);

		UnidadNegocio nuevaUnidadNegocio = unidadNegocioRepository.save(unidadNegocio);
		return mapearDTO(nuevaUnidadNegocio);
	}

	@Override
	public List<UnidadNegocioDTO> obtenerUnidadesPorFirmaId(long firmaId) {
		List<UnidadNegocio> unidadesNegocio = unidadNegocioRepository.findByFirmaId(firmaId);
		return unidadesNegocio.stream().map(unidad -> mapearDTO(unidad)).collect(Collectors.toList());
	}

	@Override
	public UnidadNegocioDTO obtenerUnidadPorId(Long firmaId, Long unidadId) {
		Firma firma = firmaRepository.findById(firmaId)
				.orElseThrow(() -> new ResourceNotFoundException("Firma", "id", firmaId));
		UnidadNegocio unidadNegocio = unidadNegocioRepository.findById(unidadId)
				.orElseThrow(() -> new ResourceNotFoundException("Unidad de Negocio", "id", unidadId));

		if (!unidadNegocio.getFirma().getId().equals(firma.getId())) {
			throw new AcsystemAppException(HttpStatus.BAD_REQUEST, "La unidad de negocio no es de esta firma");
		}

		return mapearDTO(unidadNegocio);
	}

	@Override
	public UnidadNegocioDTO actualizarUnidadNegocio(Long firmaId, Long unidadId, UnidadNegocioDTO unidadNegocioDTO) {
		Firma firma = firmaRepository.findById(firmaId)
				.orElseThrow(() -> new ResourceNotFoundException("Firma", "id", firmaId));

		UnidadNegocio unidadNegocio = unidadNegocioRepository.findById(unidadId)
				.orElseThrow(() -> new ResourceNotFoundException("Unidad de Negocio", "id", unidadId));

		if (!unidadNegocio.getFirma().getId().equals(firma.getId())) {
			throw new AcsystemAppException(HttpStatus.BAD_REQUEST, "La unidad de negocio no es de esta firma");
		}

		unidadNegocio.setNombre(unidadNegocioDTO.getNombre());
		unidadNegocio.setActivo(unidadNegocioDTO.isActivo());

		UnidadNegocio unidadActualizada = unidadNegocioRepository.save(unidadNegocio);
		return mapearDTO(unidadActualizada);
	}

	@Override
	public void eliminarUnidadNegocio(Long firmaId, Long unidadId) {
		Firma firma = firmaRepository.findById(firmaId)
				.orElseThrow(() -> new ResourceNotFoundException("Firma", "id", firmaId));

		UnidadNegocio unidadNegocio = unidadNegocioRepository.findById(unidadId)
				.orElseThrow(() -> new ResourceNotFoundException("Unidad de Negocio", "id", unidadId));

		if (!unidadNegocio.getFirma().getId().equals(firma.getId())) {
			throw new AcsystemAppException(HttpStatus.BAD_REQUEST, "La unidad de negocio no es de esta firma");
		}

		unidadNegocioRepository.delete(unidadNegocio);
	}

	// Convierte de Entidad a DTO
	private UnidadNegocioDTO mapearDTO(UnidadNegocio unidadNegocio) {
		UnidadNegocioDTO unidadNegocioDTO = modelMapper.map(unidadNegocio, UnidadNegocioDTO.class);
		return unidadNegocioDTO;
	}

	// Convierte de DTO a Entidad
	private UnidadNegocio mapearEntidad(UnidadNegocioDTO unidadNegocioDTO) {
		UnidadNegocio unidadNegocio = modelMapper.map(unidadNegocioDTO, UnidadNegocio.class);
		return unidadNegocio;
	}

}
