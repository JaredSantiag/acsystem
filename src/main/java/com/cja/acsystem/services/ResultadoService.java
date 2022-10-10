package com.cja.acsystem.services;

import com.cja.acsystem.dto.ResultadoDTO;

import java.util.List;

public interface ResultadoService {
    public List<ResultadoDTO> obtenerResultadosPorAccion(long firmaId, long unidadNegocioId,long accionId);
    public ResultadoDTO crearResultado(long firmaId, long unidadNegocioId,long accionId,ResultadoDTO resultadoDTO);
    public ResultadoDTO actualizarResultado(Long firmaId, Long unidadNegocioId,Long accionId, Long resuldatoId,ResultadoDTO resultadoDTO);
}
