package com.cja.acsystem.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpStatus;

import com.cja.acsystem.dto.CreditoStatusDTO;
import com.cja.acsystem.services.CreditoStatusService;

@RestController
@RequestMapping("acsystem/creditos/status")
public class CreditoStatusController {
	
	@Autowired
	private CreditoStatusService creditoStatusService;
	
	@GetMapping
	public List<CreditoStatusDTO> listarCreditosStatus(){
		return creditoStatusService.obtenerCreditosStatus();
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<CreditoStatusDTO> ObtenerCreditoStatusPorId(@PathVariable(name = "id") Long id){
		return ResponseEntity.ok(creditoStatusService.obtenerCreditoStatusPorId(id));
	}
	
	@PreAuthorize("hasRole('ADMIN')")
	@PostMapping
	public ResponseEntity<CreditoStatusDTO> crearCreditoStatus(@Valid @RequestBody CreditoStatusDTO creditoStatusDTO){
		return new ResponseEntity<>(creditoStatusService.crearCreditoStatus(creditoStatusDTO), HttpStatus.CREATED);
	}
	
	@PreAuthorize("hasRole('ADMIN')")
	@PutMapping("/{id}")
	public ResponseEntity<CreditoStatusDTO> actualizarCreditoStatus(@Valid @RequestBody CreditoStatusDTO creditoStatusDTO, 
			@PathVariable(name = "id") long id){
		CreditoStatusDTO creditoStatusRespuesta = creditoStatusService.actualizarCreditoStatus(id, creditoStatusDTO);
		return new ResponseEntity<>(creditoStatusRespuesta, HttpStatus.OK);
	}
	
	@PreAuthorize("hasRole('ADMIN')")
	@DeleteMapping("/{id}")
	public ResponseEntity<String> eliminarCreditoStatus(@PathVariable(name = "id") long id){
		creditoStatusService.eliminarCreditoStatus(id);
		return new ResponseEntity<>("Credito Estatus Eliminado", HttpStatus.OK);
	}
}
