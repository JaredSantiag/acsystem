package com.cja.acsystem.services;

import com.cja.acsystem.dto.ResultadoDTO;
import com.cja.acsystem.dto.SubResultadoDTO;

import java.util.List;

public interface SubResultadoService {
    public List<SubResultadoDTO> obtenerSubResultadosPorResultado(long firmaId, long unidadNegocioId, long accionId, long resultadoId);
    public SubResultadoDTO crearSubResultado(long firmaId, long unidadNegocioId,long accionId,long resultadoId,SubResultadoDTO subResultadoDTO);

    public SubResultadoDTO actualizarSubResultado(Long firmaId, Long unidadNegocioId,Long accionId, Long resuldatoId,Long subResultadoId,SubResultadoDTO subResultadoDTO);
}