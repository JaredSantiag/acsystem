package com.cja.acsystem.services;

import java.util.List;

import com.cja.acsystem.dto.CreditoStatusDTO;

public interface CreditoStatusService {

	public CreditoStatusDTO crearCreditoStatus(CreditoStatusDTO creditoStatusDTO);
	
	public List<CreditoStatusDTO> obtenerCreditosStatus();
	
	public CreditoStatusDTO obtenerCreditoStatusPorId(Long id);
	
	public CreditoStatusDTO actualizarCreditoStatus(Long id, CreditoStatusDTO creditoStatusDTO);
	
	public void eliminarCreditoStatus(Long id);
	
}
