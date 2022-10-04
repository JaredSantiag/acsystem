package com.cja.acsystem.controllers;

import java.util.List;

import javax.validation.Valid;

import com.cja.acsystem.dto.AccionDTO;
import com.cja.acsystem.services.AccionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/acsystem/")
public class AccionController {

    @Autowired
    AccionService accionService;

    @GetMapping("/firmas/{firmaId}/acciones")
    @CrossOrigin(origins = "http://localhost:3000",methods = RequestMethod.GET)
    public List<AccionDTO> listarAccionPorFirmas(@PathVariable(value="firmaId") Long firmaId){
        return accionService.obtenerAccionesPorFirmaId(firmaId);
    }

    @GetMapping("/firmas/{firmaId}/acciones/{id}")
    public ResponseEntity<AccionDTO> obtenerAccionesPorId(@PathVariable(value="firmaId") Long firmaId,@PathVariable(value="id") Long accionId){
        AccionDTO accionDTO = accionService.obtenerAccionPorId(firmaId, accionId);
        return new ResponseEntity<>(accionDTO, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/firmas/{firmaId}/acciones")
    public ResponseEntity<AccionDTO> guardarAccion(@PathVariable(value="firmaId") long firmaId,@Valid @RequestBody AccionDTO accionDTO){
        return new ResponseEntity<>(accionService.crearAccion(firmaId, accionDTO),HttpStatus.CREATED);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/firmas/{firmaId}/acciones/{accionId}")
    public ResponseEntity<AccionDTO> actualizarAccion(@PathVariable(value="firmaId") Long firmaId,@PathVariable(value="accionId") Long accionId,@Valid @RequestBody AccionDTO accionDTO){
        AccionDTO accionActualizada = accionService.actualizarAccion(firmaId, accionId, accionDTO);
        return new ResponseEntity<>(accionActualizada,HttpStatus.OK);
    }

}
