package com.cja.acsystem.services;

import java.util.List;
import java.util.stream.Collectors;

import com.cja.acsystem.dto.ResultadoDTO;
import com.cja.acsystem.entities.Accion;
import com.cja.acsystem.entities.Resultado;
import com.cja.acsystem.repositories.AccionRepository;
import com.cja.acsystem.repositories.ResultadoRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.cja.acsystem.dto.UnidadNegocioDTO;
import com.cja.acsystem.entities.Firma;
import com.cja.acsystem.entities.UnidadNegocio;
import com.cja.acsystem.exceptions.AcsystemAppException;
import com.cja.acsystem.exceptions.ResourceNotFoundException;

@Service
public class ResultadoServicelmpl implements ResultadoService {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private ResultadoRepository resultadoRepository;

    @Autowired
    private AccionRepository accionRepository;

    @Override
    public ResultadoDTO crearResultado(long accionId, ResultadoDTO resultadoDTO) {
        Resultado resultado = mapearEntidad(resultadoDTO);
        Accion accion = accionRepository.findById(accionId)
                .orElseThrow(() -> new ResourceNotFoundException("Accion", "id", accionId));
        resultado.setAccion(accion);

        Resultado nuevoResultado = resultadoRepository.save(resultado);
        return mapearDTO(nuevoResultado);
    }

    @Override
    public List<ResultadoDTO> obtenerResultadosPorAccionId(long accionId) {
        List<Resultado> resultados = resultadoRepository.findByAccionId(accionId);
        return resultados.stream().map(resultado -> mapearDTO(resultado)).collect(Collectors.toList());
    }

    @Override
    public ResultadoDTO obtenerResultadoPorId(Long accionId, Long resultadoId) {
        Accion accion = accionRepository.findById(accionId)
                .orElseThrow(() -> new ResourceNotFoundException("Accion", "id", accionId));
        Resultado resultado = resultadoRepository.findById(resultadoId)
                .orElseThrow(() -> new ResourceNotFoundException("Resultado", "id", resultadoId));

        if (!resultado.getAccion().getId().equals(accion.getId())) {
            throw new AcsystemAppException(HttpStatus.BAD_REQUEST, "El resultado no es de esta firma");
        }

        return mapearDTO(resultado);
    }

    @Override
    public ResultadoDTO actualizarResultado(Long accionId, Long resultadoId, ResultadoDTO resultadoDTO) {
        Accion accion = accionRepository.findById(accionId)
                .orElseThrow(() -> new ResourceNotFoundException("Accion", "id", accionId));

        Resultado resultado = resultadoRepository.findById(resultadoId)
                .orElseThrow(() -> new ResourceNotFoundException("Resultado", "id", resultadoId));

        if (!resultado.getAccion().getId().equals(accion.getId())) {
            throw new AcsystemAppException(HttpStatus.BAD_REQUEST, "El resultado no es de esta en la accion");
        }

        resultado.setDescripcion(resultadoDTO.getDescripcion());
        resultado.setActivo(resultadoDTO.isActivo());

        Resultado resultadoActualizado = resultadoRepository.save(resultado);
        return mapearDTO(resultadoActualizado);
    }

    // Convierte de Entidad a DTO
    private ResultadoDTO mapearDTO(Resultado resultado) {
        ResultadoDTO resultadoDTO = modelMapper.map(resultado, ResultadoDTO.class);
        return resultadoDTO;
    }

    // Convierte de DTO a Entidad
    private Resultado mapearEntidad(ResultadoDTO resultadoDTO) {
        Resultado resultado = modelMapper.map(resultadoDTO, Resultado.class);
        return resultado;
    }

}

