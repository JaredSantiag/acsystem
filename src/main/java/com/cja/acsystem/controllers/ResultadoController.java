package com.cja.acsystem.controllers;

import java.util.List;

import javax.validation.Valid;

import com.cja.acsystem.dto.AccionDTO;
import com.cja.acsystem.dto.ResultadoDTO;
import com.cja.acsystem.services.AccionService;
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


    @GetMapping("/firmas/{firmaId}/unidadesNegocio/{unidadNegocioId}/acciones/{accionId}/resultados")
    @CrossOrigin(origins = "http://localhost:3000",methods = RequestMethod.GET)
    public List<ResultadoDTO> listarResultadosPorAcciones(@PathVariable(value="firmaId") Long firmaId, @PathVariable(value="unidadNegocioId") Long unidadNegocioId, @PathVariable(value="accionId") Long accionId){
        return resultadoService.obtenerResultadosPorAccion(firmaId,unidadNegocioId,accionId);
    }



    /*
    @GetMapping("/firmas/{firmaId}/unidadesNegocio/{unidadNegocioId}/acciones/{id}")
    public ResponseEntity<AccionDTO> obtenerAccionesPorId(@PathVariable(value="firmaId") Long firmaId,@PathVariable(value="unidadNegocioId") Long unidadNegocioId,@PathVariable(value="id") Long accionId){
        AccionDTO accionDTO = accionService.obtenerAccionPorId(firmaId, unidadNegocioId,accionId);
        return new ResponseEntity<>(accionDTO, HttpStatus.OK);
    }
     */

    //@PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/firmas/{firmaId}/unidadesNegocio/{unidadNegocioId}/acciones/{accionId}/resultados")
    public ResponseEntity<ResultadoDTO> guardarResultado(@PathVariable(value="firmaId") long firmaId,@PathVariable(value="unidadNegocioId") Long unidadNegocioId,@PathVariable(value="accionId") Long accionId, @Valid @RequestBody ResultadoDTO resultadoDTO){
        return new ResponseEntity<>(resultadoService.crearResultado(firmaId, unidadNegocioId,accionId,resultadoDTO),HttpStatus.CREATED);
    }



    //@PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/firmas/{firmaId}/unidadesNegocio/{unidadNegocioId}/acciones/{accionId}/resultados/{resultadoId}")
    public ResponseEntity<ResultadoDTO> actualizarResultado(@PathVariable(value="firmaId") Long firmaId,@PathVariable(value="unidadNegocioId") Long unidadNegocioId,@PathVariable(value="accionId") Long accionId,@PathVariable(value="resultadoId") Long resultadoId,@Valid @RequestBody ResultadoDTO resultadoDTO){
        ResultadoDTO resultadoActualizado = resultadoService.actualizarResultado(firmaId, unidadNegocioId,accionId, resultadoId,resultadoDTO);
        return new ResponseEntity<>(resultadoActualizado,HttpStatus.OK);
    }

}
