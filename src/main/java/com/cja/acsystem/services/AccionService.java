package com.cja.acsystem.services;

import java.util.List;

import com.cja.acsystem.dto.AccionDTO;
import com.cja.acsystem.dto.UnidadNegocioDTO;

public interface AccionService {
    public AccionDTO crearAccion(long firmaId, AccionDTO accionDTO);

    public List<AccionDTO> obtenerAccionesPorFirmaId(long FirmaId);

    public AccionDTO obtenerAccionPorId(Long firmaId, Long accionId);

    public AccionDTO actualizarAccion(Long firmaId, Long accionId, AccionDTO accionDTO);

}
