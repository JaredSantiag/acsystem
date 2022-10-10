package com.cja.acsystem.services;

import com.cja.acsystem.dto.ResultadoDTO;
import com.cja.acsystem.entities.*;
import com.cja.acsystem.exceptions.AcsystemAppException;
import com.cja.acsystem.exceptions.ResourceNotFoundException;
import com.cja.acsystem.repositories.AccionRepository;
import com.cja.acsystem.repositories.FirmaRepository;
import com.cja.acsystem.repositories.ResultadoRepository;
import com.cja.acsystem.repositories.UnidadNegocioRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ResultadoServicelmpl implements ResultadoService {

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

    @Override
    public ResultadoDTO crearResultado(long firmaId, long unidadNegocioId,long accionId,ResultadoDTO resultadoDTO) {
        Resultado resultado = mapearEntidad(resultadoDTO);

        Accion accion = accionRepository.findById(accionId)
                .orElseThrow(() -> new ResourceNotFoundException("Accion ", "id", accionId));
        resultado.setAccion(accion);

        Resultado nuevoResultado = resultadoRepository.save(resultado);
        return mapearDTO(nuevoResultado);
    }

    @Override
    public List<ResultadoDTO> obtenerResultadosPorAccion(long firmaId, long unidadNegocioId, long accionId) {
        List<Resultado> resultados = resultadoRepository.findByAccionId(accionId);

        return resultados.stream().map(resultado -> mapearDTO(resultado)).collect(Collectors.toList());
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
    public ResultadoDTO actualizarResultado(Long firmaId, Long unidadNegocioId,Long accionId, Long resultadoId,ResultadoDTO resultadoDTO) {
        Firma firma = firmaRepository.findById(firmaId)
                .orElseThrow(() -> new ResourceNotFoundException("Firma", "id", firmaId));

        UnidadNegocio unidadNegocio = unidadNegocioRepository.findById(unidadNegocioId)
                .orElseThrow(() -> new ResourceNotFoundException("Unidad de Negocio", "id", unidadNegocioId));

        Accion accion = accionRepository.findById(accionId)
                .orElseThrow(() -> new ResourceNotFoundException("Accion", "id", accionId));

        Resultado resultado = resultadoRepository.findById(resultadoId)
                .orElseThrow(() -> new ResourceNotFoundException("Resultado", "id", resultadoId));

        Boolean result = ((accion.getFirma().equals(firma.getId())) && (accion.getUnidadNegocio().equals(unidadNegocio.getId()) && (resultado.getAccion().equals(accion.getId()))));

        if (result) {
            throw new AcsystemAppException(HttpStatus.BAD_REQUEST, "El resultado no es de esta disponible en la BD");
        }

        resultado.setDescripcion(resultadoDTO.getDescripcion());
        resultado.setAutoriza(resultadoDTO.isAutoriza());
        resultado.setCodigoResultado(resultadoDTO.getCodigoResultado());
        resultado.setContacto(resultadoDTO.isContacto());
        resultado.setPromesa(resultadoDTO.isPromesa());

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
