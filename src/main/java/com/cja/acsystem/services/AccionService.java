package com.cja.acsystem.services;

import java.util.List;

import com.cja.acsystem.dto.AccionDTO;

public interface AccionService {
    public AccionDTO crearAccion(long firmaId, long unidadNegocioId,AccionDTO accionDTO);

    public List<AccionDTO> obtenerAccionesPorUnidadNegocio(long firmaId, long unidadNegocioId);

    /*
    public AccionDTO obtenerAccionPorId(Long firmaId, Long unidadNegocioId,String accionId);
    */
    public AccionDTO actualizarAccion(Long firmaId, Long unidadNegocioId,Long accionId, AccionDTO accionDTO);

}
