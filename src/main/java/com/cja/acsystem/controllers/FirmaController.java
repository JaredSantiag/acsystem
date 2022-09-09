package com.cja.acsystem.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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

import com.cja.acsystem.dto.FirmaDTO;
import com.cja.acsystem.services.FirmaService;

@RestController
@RequestMapping("acsystem/firmas")
public class FirmaController {

	@Autowired
	private FirmaService firmaService;
	
	@GetMapping
	public List<FirmaDTO> listarFirmas() {
		return firmaService.obtenerFirmas();
	}
	
	@PreAuthorize("hasRole('USER')")
	@GetMapping("/{id}")
	public ResponseEntity<FirmaDTO> obtenerFirmaPorId(@PathVariable(name = "id") Long id) {
		return ResponseEntity.ok(firmaService.obtenerFirmaPorId(id));
	}

	@PreAuthorize("hasRole('ADMIN')")
	@PostMapping
	public ResponseEntity<FirmaDTO> guardarFirma(@Valid @RequestBody FirmaDTO firmaDTO) {
		return new ResponseEntity<>(firmaService.crearFirma(firmaDTO), HttpStatus.CREATED);
	}

	@PreAuthorize("hasRole('ADMIN')")
	@PutMapping("/{id}")
	public ResponseEntity<FirmaDTO> actualizarFirma(@Valid @RequestBody FirmaDTO firmaDTO,
		@PathVariable(name = "id") long id) {
		FirmaDTO firmaRespuesta = firmaService.actualizarFirma(firmaDTO, id);
		return new ResponseEntity<>(firmaRespuesta, HttpStatus.OK);
	}

	@PreAuthorize("hasRole('ADMIN')")
	@DeleteMapping("/{id}")
	public ResponseEntity<String> eliminarFirma(@PathVariable(name = "id") long id) {
		firmaService.eliminarFirma(id);
		return new ResponseEntity<>("Firma eliminada", HttpStatus.OK);
	}
	
}
