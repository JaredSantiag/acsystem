package com.cja.acsystem.services;

import com.cja.acsystem.dto.ResultadoDTO;
import com.cja.acsystem.dto.SubResultadoDTO;
import com.cja.acsystem.entities.*;
import com.cja.acsystem.exceptions.AcsystemAppException;
import com.cja.acsystem.exceptions.ResourceNotFoundException;
import com.cja.acsystem.repositories.*;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SubResultadoServicelmpl implements SubResultadoService {

    @Autowired
    private ModelMapper modelMapper;


    @Autowired
    private AccionRepository accionRepository;

    @Autowired
    private FirmaRepository firmaRepository;

    @Autowired
    private UnidadNegocioRepository unidadNegocioRepository;
    @Autowired
    private ResultadoRepository resultadoRepository;
    @Autowired
    private SubResultadoRepository subResultadoRepository;



    @Override
    public SubResultadoDTO crearSubResultado(long firmaId, long unidadNegocioId,long accionId,long resultadoId,SubResultadoDTO subResultadoDTO) {
        SubResultado subResultado = mapearEntidad(subResultadoDTO);

        Resultado resultado = resultadoRepository.findById(resultadoId)
                .orElseThrow(() -> new ResourceNotFoundException("Resultado ", "id", resultadoId));
        subResultado.setResultado(resultado);

        SubResultado nuevoSubResultado = subResultadoRepository.save(subResultado);
        return mapearDTO(nuevoSubResultado);
    }



    @Override
    public List<SubResultadoDTO> obtenerSubResultadosPorResultado(long firmaId, long unidadNegocioId, long accionId,long resultadoId) {
        List<SubResultado> subResultados = subResultadoRepository.findResultadoById(resultadoId);

        return subResultados.stream().map(subResultado -> mapearDTO(subResultado)).collect(Collectors.toList());
    }





    /*
    @Override
    public AccionDTO obtenerAccionPorId(Long firmaId,Long unidadNegocioId, String accionId) {
        Firma firma = firmaRepository.findById(firmaId)
                .orElseThrow(() -> new ResourceNotFoundException("Firma", "id", firmaId));

        UnidadNegocio unidadNegocio = unidadNegocioRepository.findById(unidadNegocioId)
                .orElseThrow(() -> new ResourceNotFoundException("Unidad Negocio", "id", unidadNegocioId));

         //BUSCAR COMO USAR STRINGS para findById. Si no, entonces buscar alternativas
        Accion accion = accionRepository.findById(accionId)
                .orElseThrow(() -> new ResourceNotFoundExceptionString("Accion", "id", accionId));


        if (!accion.getFirma().getId().equals(firma.getId())) {
            throw new AcsystemAppException(HttpStatus.BAD_REQUEST, "La accion no es de esta firma o de esta unidad de negocio");
        }


        return mapearDTO(accion);
    }

     */

    @Override
    public SubResultadoDTO actualizarSubResultado(Long firmaId, Long unidadNegocioId,Long accionId, Long resultadoId,Long subResultadoId,SubResultadoDTO subResultadoDTO) {
        Firma firma = firmaRepository.findById(firmaId)
                .orElseThrow(() -> new ResourceNotFoundException("Firma", "id", firmaId));

        UnidadNegocio unidadNegocio = unidadNegocioRepository.findById(unidadNegocioId)
                .orElseThrow(() -> new ResourceNotFoundException("Unidad de Negocio", "id", unidadNegocioId));

        Accion accion = accionRepository.findById(accionId)
                .orElseThrow(() -> new ResourceNotFoundException("Accion", "id", accionId));

        Resultado resultado = resultadoRepository.findById(resultadoId)
                .orElseThrow(() -> new ResourceNotFoundException("Resultado", "id", resultadoId));

        SubResultado subResultado = subResultadoRepository.findById(subResultadoId)
                .orElseThrow(() -> new ResourceNotFoundException("Sub Resultado", "id", subResultadoId));

        Boolean result = ((accion.getFirma().equals(firma.getId())) && (accion.getUnidadNegocio().equals(unidadNegocio.getId()) && (resultado.getAccion().equals(accion.getId())) && (subResultado.getResultado().equals(resultado.getId()))));

        if (result) {
            throw new AcsystemAppException(HttpStatus.BAD_REQUEST, "El Sub Resultado no es de esta disponible en la BD");
        }

        subResultado.setDescripcion(subResultadoDTO.getDescripcion());
        subResultado.setCodigoSubResultado(subResultadoDTO.getCodigoSubResultado());
        subResultado.setTexto(subResultadoDTO.getTexto());
        subResultado.setActivo(subResultadoDTO.isActivo());

        SubResultado subResultadoActualizado = subResultadoRepository.save(subResultado);
        return mapearDTO(subResultadoActualizado);
    }


    // Convierte de Entidad a DTO
    private SubResultadoDTO mapearDTO(SubResultado subResultado) {
        SubResultadoDTO subResultadoDTO = modelMapper.map(subResultado, SubResultadoDTO.class);
        return subResultadoDTO;
    }

    // Convierte de DTO a Entidad
    private SubResultado mapearEntidad(SubResultadoDTO subResultadoDTO) {
        SubResultado subResultado = modelMapper.map(subResultadoDTO, SubResultado.class);
        return subResultado;
    }
}
