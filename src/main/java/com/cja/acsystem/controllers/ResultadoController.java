package com.cja.acsystem.controllers;

import java.util.List;

import javax.validation.Valid;

import com.cja.acsystem.dto.ResultadoDTO;
import com.cja.acsystem.services.ResultadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;


@RestController
@CrossOrigin
@RequestMapping("/acsystem/")
public class ResultadoController {

    @Autowired
    ResultadoService resultadoService;

    @GetMapping("/acciones/{accionId}/resultados")
    @CrossOrigin(origins = "http://localhost:3000",methods = RequestMethod.GET)
    public List<ResultadoDTO> listarResultadosPorAccion(@PathVariable(value="accionId") Long accionId){
        return resultadoService.obtenerResultadosPorAccionId(accionId);
    }

    @GetMapping("/acciones/{accionId}/resultados/{id}")
    public ResponseEntity<ResultadoDTO> obtenerResultadosPorId(@PathVariable(value="accionId") Long accionId,@PathVariable(value="id") Long resultadoId){
        ResultadoDTO resultadoDTO = resultadoService.obtenerResultadoPorId(accionId, resultadoId);
        return new ResponseEntity<>(resultadoDTO, HttpStatus.OK);
    }

    //@PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/acciones/{accionId}/resultados")
    public ResponseEntity<ResultadoDTO> guardarResultado(@PathVariable(value="accionId") long accionId,@Valid @RequestBody ResultadoDTO resultadoDTO){
        return new ResponseEntity<>(resultadoService.crearResultado(accionId, resultadoDTO),HttpStatus.CREATED);
    }

    //@PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/acciones/{accionId}/resultados/{resultadoId}")
    public ResponseEntity<ResultadoDTO> actualizarResultados(@PathVariable(value="accionId") Long accionId,@PathVariable(value="resultadoId") Long resultadoId,@Valid @RequestBody ResultadoDTO resultadoDTO) {
        ResultadoDTO resultadoActualizado = resultadoService.actualizarResultado(accionId, resultadoId, resultadoDTO);
        return new ResponseEntity<>(resultadoActualizado, HttpStatus.OK);
    }
}
