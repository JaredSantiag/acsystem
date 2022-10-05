package com.cja.acsystem.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import com.cja.acsystem.dto.FirmaDTO;
import com.cja.acsystem.services.FirmaService;

@RestController
@CrossOrigin

@RequestMapping("acsystem/firmas")
public class FirmaController {

	@Autowired
	private FirmaService firmaService;
	
	@GetMapping
	@CrossOrigin(origins = "http://localhost:3000",methods = RequestMethod.GET)
	public List<FirmaDTO> listarFirmas() {
		return firmaService.obtenerFirmas();
	}
	
	//@PreAuthorize("hasRole('USER')")
	@GetMapping("/{id}")
	public ResponseEntity<FirmaDTO> obtenerFirmaPorId(@PathVariable(name = "id") Long id) {
		return ResponseEntity.ok(firmaService.obtenerFirmaPorId(id));
	}

	@PreAuthorize("hasRole('ADMIN')")
	@PostMapping
	@CrossOrigin(origins = "http://localhost:3000",methods = RequestMethod.POST)
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
