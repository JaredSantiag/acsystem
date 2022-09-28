package com.cja.acsystem.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.cja.acsystem.services.UnidadNegocioService;
import com.cja.acsystem.dto.UnidadNegocioDTO;


@RestController
@CrossOrigin
@RequestMapping("/acsystem/")
public class UnidadNegocioController {

	@Autowired
	UnidadNegocioService unidadNegocioService;
	
	@GetMapping("/firmas/{firmaId}/unidadesNegocio")
	@CrossOrigin(origins = "http://localhost:3000",methods = RequestMethod.GET)
	public List<UnidadNegocioDTO> listarUnidadesPorFirmas(@PathVariable(value="firmaId") Long firmaId){
		return unidadNegocioService.obtenerUnidadesPorFirmaId(firmaId);
	}
	
	@GetMapping("/firmas/{firmaId}/unidadesNegocio/{id}")
	public ResponseEntity<UnidadNegocioDTO> obtenerUnidadesPorId(@PathVariable(value="firmaId") Long firmaId,@PathVariable(value="id") Long unidadId){
		UnidadNegocioDTO unidadNegocioDTO = unidadNegocioService.obtenerUnidadPorId(firmaId, unidadId);
		return new ResponseEntity<>(unidadNegocioDTO, HttpStatus.OK);
	}
	
	@PostMapping("/firmas/{firmaId}/unidadesNegocio")
	public ResponseEntity<UnidadNegocioDTO> guardarUnidad(@PathVariable(value="firmaId") long firmaId,@Valid @RequestBody UnidadNegocioDTO unidadNegocioDTO){
		return new ResponseEntity<>(unidadNegocioService.crearUnidadNegocio(firmaId, unidadNegocioDTO),HttpStatus.CREATED);
	}
	
	@PutMapping("/firmas/{firmaId}/unidadesNegocio/{unidadId}")
	public ResponseEntity<UnidadNegocioDTO> actualizarUnidadNegocio(@PathVariable(value="firmaId") Long firmaId,@PathVariable(value="unidadId") Long unidadId,@Valid @RequestBody UnidadNegocioDTO unidadNegocioDTO){
		UnidadNegocioDTO unidadNegocioActualizada = unidadNegocioService.actualizarUnidadNegocio(firmaId, unidadId, unidadNegocioDTO);
		return new ResponseEntity<>(unidadNegocioActualizada,HttpStatus.OK);
	}
	
	@DeleteMapping("/firmas/{firmaId}/unidadesNegocio/{id}")
	@CrossOrigin(origins = "http://localhost:3000")
	public ResponseEntity<String> eliminarUnidadNegocio(@PathVariable(value="firmaId")
															Long firmaId,@PathVariable(value="id") Long unidadId){
		unidadNegocioService.eliminarUnidadNegocio(firmaId, unidadId);
		return new ResponseEntity<>("Unidad de Negocio eliminada", HttpStatus.OK);
	}

}
