package com.cja.acsystem.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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

import com.cja.acsystem.dto.FirmaDTO;
import com.cja.acsystem.services.FirmaService;

@RestController
<<<<<<< HEAD
@CrossOrigin
=======
>>>>>>> 8e018d77a8e1381877d136ce32169a939f479df4
@RequestMapping("acsystem/firmas")
public class FirmaController {

	@Autowired
	private FirmaService firmaService;
	
	@GetMapping
<<<<<<< HEAD
	@CrossOrigin(origins = "http://localhost:3000",methods = RequestMethod.GET)
=======
>>>>>>> 8e018d77a8e1381877d136ce32169a939f479df4
	public List<FirmaDTO> listarFirmas() {
		return firmaService.obtenerFirmas();
	}
	
<<<<<<< HEAD
	//@PreAuthorize("hasRole('USER')")
=======
	@PreAuthorize("hasRole('USER')")
>>>>>>> 8e018d77a8e1381877d136ce32169a939f479df4
	@GetMapping("/{id}")
	public ResponseEntity<FirmaDTO> obtenerFirmaPorId(@PathVariable(name = "id") Long id) {
		return ResponseEntity.ok(firmaService.obtenerFirmaPorId(id));
	}

	@PreAuthorize("hasRole('ADMIN')")
	@PostMapping
<<<<<<< HEAD
	@CrossOrigin(origins = "http://localhost:3000",methods = RequestMethod.POST)
=======
>>>>>>> 8e018d77a8e1381877d136ce32169a939f479df4
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
