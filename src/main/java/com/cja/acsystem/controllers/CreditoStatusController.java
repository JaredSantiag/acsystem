package com.cja.acsystem.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
<<<<<<< HEAD
import org.springframework.web.bind.annotation.*;
=======
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
>>>>>>> 8e018d77a8e1381877d136ce32169a939f479df4
import org.springframework.http.HttpStatus;

import com.cja.acsystem.dto.CreditoStatusDTO;
import com.cja.acsystem.services.CreditoStatusService;

@RestController
<<<<<<< HEAD
@CrossOrigin(origins="http://localhost:3000")
@RequestMapping("acsystem/creditos/status")
public class CreditoStatusController {

	@Autowired
	private CreditoStatusService creditoStatusService;

=======
@RequestMapping("acsystem/creditos/status")
public class CreditoStatusController {
	
	@Autowired
	private CreditoStatusService creditoStatusService;
	
>>>>>>> 8e018d77a8e1381877d136ce32169a939f479df4
	@GetMapping
	public List<CreditoStatusDTO> listarCreditosStatus(){
		return creditoStatusService.obtenerCreditosStatus();
	}
<<<<<<< HEAD

	@GetMapping("/{id}")
	@CrossOrigin(origins = "http://localhost:3000",methods = RequestMethod.GET)
	public ResponseEntity<CreditoStatusDTO> ObtenerCreditoStatusPorId(@PathVariable(name = "id") Long id){
		return ResponseEntity.ok(creditoStatusService.obtenerCreditoStatusPorId(id));
	}

	@PreAuthorize("hasRole('ADMIN')")
	@PostMapping
	@CrossOrigin(origins = "http://localhost:3000",methods = RequestMethod.POST)
	public ResponseEntity<CreditoStatusDTO> crearCreditoStatus(@Valid @RequestBody CreditoStatusDTO creditoStatusDTO){
		return new ResponseEntity<>(creditoStatusService.crearCreditoStatus(creditoStatusDTO), HttpStatus.CREATED);
	}

	@PreAuthorize("hasRole('ADMIN')")
	@PutMapping("/{id}")
	@CrossOrigin(origins = "http://localhost:3000",methods = RequestMethod.PUT)
	public ResponseEntity<CreditoStatusDTO> actualizarCreditoStatus(@Valid @RequestBody CreditoStatusDTO creditoStatusDTO,
=======
	
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
>>>>>>> 8e018d77a8e1381877d136ce32169a939f479df4
			@PathVariable(name = "id") long id){
		CreditoStatusDTO creditoStatusRespuesta = creditoStatusService.actualizarCreditoStatus(id, creditoStatusDTO);
		return new ResponseEntity<>(creditoStatusRespuesta, HttpStatus.OK);
	}
<<<<<<< HEAD

	@PreAuthorize("hasRole('ADMIN')")
	@CrossOrigin(origins = "http://localhost:3000")
=======
	
	@PreAuthorize("hasRole('ADMIN')")
>>>>>>> 8e018d77a8e1381877d136ce32169a939f479df4
	@DeleteMapping("/{id}")
	public ResponseEntity<String> eliminarCreditoStatus(@PathVariable(name = "id") long id){
		creditoStatusService.eliminarCreditoStatus(id);
		return new ResponseEntity<>("Credito Estatus Eliminado", HttpStatus.OK);
	}
}
