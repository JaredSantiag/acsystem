package com.cja.acsystem.services;

import com.cja.acsystem.dto.ResultadoDTO;
import com.cja.acsystem.dto.UnidadNegocioDTO;

import java.util.List;

public interface ResultadoService {
    public ResultadoDTO crearResultado(long accionId, ResultadoDTO resultadoDTO);

    public List<ResultadoDTO> obtenerResultadosPorAccionId(long accionId);

    public ResultadoDTO obtenerResultadoPorId(Long accionId, Long resultadoId);

    public ResultadoDTO actualizarResultado(Long accionId, Long resultadoId, ResultadoDTO resultadoDTO);

}
