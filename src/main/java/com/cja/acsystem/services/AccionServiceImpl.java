package com.cja.acsystem.services;

import java.util.List;
import java.util.stream.Collectors;

import com.cja.acsystem.dto.AccionDTO;
import com.cja.acsystem.entities.Accion;
import com.cja.acsystem.repositories.AccionRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import com.cja.acsystem.entities.Firma;
import com.cja.acsystem.exceptions.AcsystemAppException;
import com.cja.acsystem.exceptions.ResourceNotFoundException;
import com.cja.acsystem.repositories.FirmaRepository;

@Service
public class AccionServiceImpl implements AccionService {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private AccionRepository accionRepository;

    @Autowired
    private FirmaRepository firmaRepository;

    @Override
    public AccionDTO crearAccion(long firmaId, AccionDTO accionDTO) {
        Accion accion = mapearEntidad(accionDTO);
        Firma firma = firmaRepository.findById(firmaId)
                .orElseThrow(() -> new ResourceNotFoundException("Firma", "id", firmaId));
        accion.setFirma(firma);

        Accion nuevaAccion = accionRepository.save(accion);
        return mapearDTO(nuevaAccion);
    }

    @Override
    public List<AccionDTO> obtenerAccionesPorFirmaId(long firmaId) {
        List<Accion> acciones = accionRepository.findByFirmaId(firmaId);
        return acciones.stream().map(accion -> mapearDTO(accion)).collect(Collectors.toList());
    }

    @Override
    public AccionDTO obtenerAccionPorId(Long firmaId, Long unidadId) {
        Firma firma = firmaRepository.findById(firmaId)
                .orElseThrow(() -> new ResourceNotFoundException("Firma", "id", firmaId));
        Accion accion = accionRepository.findById(unidadId)
                .orElseThrow(() -> new ResourceNotFoundException("Accion", "id", unidadId));

        if (!accion.getFirma().getId().equals(firma.getId())) {
            throw new AcsystemAppException(HttpStatus.BAD_REQUEST, "La accion no es de esta firma");
        }

        return mapearDTO(accion);
    }

    @Override
    public AccionDTO actualizarAccion(Long firmaId, Long accionId, AccionDTO accionDTO) {
        Firma firma = firmaRepository.findById(firmaId)
                .orElseThrow(() -> new ResourceNotFoundException("Firma", "id", firmaId));

        Accion accion = accionRepository.findById(accionId)
                .orElseThrow(() -> new ResourceNotFoundException("Accion", "id", accionId));

        if (!accion.getFirma().getId().equals(firma.getId())) {
            throw new AcsystemAppException(HttpStatus.BAD_REQUEST, "La accion no es de esta firma");
        }

        accion.setDescripcion(accionDTO.getDescripcion());
        accion.setActivo(accionDTO.isActivo());

        Accion accionActualizada = accionRepository.save(accion);
        return mapearDTO(accionActualizada);
    }

    // Convierte de Entidad a DTO
    private AccionDTO mapearDTO(Accion accion) {
        AccionDTO accionDTO = modelMapper.map(accion, AccionDTO.class);
        return accionDTO;
    }

    // Convierte de DTO a Entidad
    private Accion mapearEntidad(AccionDTO accionDTO) {
        Accion accion = modelMapper.map(accionDTO, Accion.class);
        return accion;
    }
}
