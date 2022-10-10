package com.cja.acsystem.controllers;

import java.util.List;

import javax.validation.Valid;

import com.cja.acsystem.dto.AccionDTO;
import com.cja.acsystem.dto.ResultadoDTO;
import com.cja.acsystem.dto.SubResultadoDTO;
import com.cja.acsystem.entities.SubResultado;
import com.cja.acsystem.services.AccionService;
import com.cja.acsystem.services.ResultadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import com.cja.acsystem.services.SubResultadoService;

@RestController
@CrossOrigin
@RequestMapping("/acsystem/")
public class SubResultadoController {

    @Autowired
    SubResultadoService subResultadoService;


    @GetMapping("/firmas/{firmaId}/unidadesNegocio/{unidadNegocioId}/acciones/{accionId}/resultados/{resultadoId}/subResultados")
    @CrossOrigin(origins = "http://localhost:3000",methods = RequestMethod.GET)
    public List<SubResultadoDTO> listarSubResultadosPorResultado(@PathVariable(value="firmaId") Long firmaId, @PathVariable(value="unidadNegocioId") Long unidadNegocioId, @PathVariable(value="accionId") Long accionId, @PathVariable(value="resultadoId") Long resultadoId){
        return subResultadoService.obtenerSubResultadosPorResultado(firmaId,unidadNegocioId,accionId,resultadoId
        );
    }

    //@PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/firmas/{firmaId}/unidadesNegocio/{unidadNegocioId}/acciones/{accionId}/resultados/{resultadoId}/subResultados")
    public ResponseEntity<SubResultadoDTO> guardarResultado(@PathVariable(value="firmaId") long firmaId,@PathVariable(value="unidadNegocioId") Long unidadNegocioId,@PathVariable(value="accionId") Long accionId, @PathVariable(value="resultadoId") Long resultadoId,@Valid @RequestBody SubResultadoDTO subResultadoDTO){
        return new ResponseEntity<>(subResultadoService.crearSubResultado(firmaId, unidadNegocioId,accionId,resultadoId,subResultadoDTO),HttpStatus.CREATED);
    }




    //@PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/firmas/{firmaId}/unidadesNegocio/{unidadNegocioId}/acciones/{accionId}/resultados/{resultadoId}/subResultados/{subResultadoId}")
    public ResponseEntity<SubResultadoDTO> actualizarSubResultado(@PathVariable(value="firmaId") Long firmaId,@PathVariable(value="unidadNegocioId") Long unidadNegocioId,@PathVariable(value="accionId") Long accionId,@PathVariable(value="resultadoId") Long resultadoId,@PathVariable(value="subResultadoId") Long subResultadoId,@Valid @RequestBody SubResultadoDTO subResultadoDTO){
        SubResultadoDTO subResultadoActualizado = subResultadoService.actualizarSubResultado(firmaId, unidadNegocioId,accionId, resultadoId,subResultadoId,subResultadoDTO);
        return new ResponseEntity<>(subResultadoActualizado,HttpStatus.OK);
    }



}
