package com.cja.acsystem.services;

import java.util.List;

import com.cja.acsystem.dto.AccionDTO;

public interface AccionService {
    public AccionDTO crearAccion(long firmaId, long unidadNegocioId,AccionDTO accionDTO);

    public List<AccionDTO> obtenerAccionesPorUnidadNegocio(long UnidadNegocioId);

    public AccionDTO obtenerAccionPorId(Long firmaId, Long unidadNegocioId,Long accionId);

    public AccionDTO actualizarAccion(Long firmaId, Long unidadNegocioId,Long accionId, AccionDTO accionDTO);

}
