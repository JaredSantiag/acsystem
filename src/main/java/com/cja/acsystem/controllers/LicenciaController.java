package com.cja.acsystem.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cja.acsystem.dto.LicenciaDTO;
import com.cja.acsystem.services.LicenciaService;

@RestController
@RequestMapping("acsystem/licencias")
public class LicenciaController {

	@Autowired
	private LicenciaService licenciaService;

	@GetMapping()
	public List<LicenciaDTO> mostrarLicencias() {
		return licenciaService.obtenerLicencias();
	}

	@GetMapping("/{id}")
	public ResponseEntity<LicenciaDTO> obtenerLicenciaPorId(@PathVariable(name = "id") Long id) {
		return ResponseEntity.ok(licenciaService.obtenerLicenciaPorId(id));
	}

	@PostMapping
	public ResponseEntity<LicenciaDTO> guardarLicencia(@RequestBody LicenciaDTO licenciaDTO) {
		return new ResponseEntity<>(licenciaService.crearLicencia(licenciaDTO), HttpStatus.CREATED);
	}

}
