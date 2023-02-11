package com.dante.osciloscopioweb.controllers;

import com.dante.osciloscopioweb.models.Lectura;
import com.dante.osciloscopioweb.services.LecturaServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE})
@RequestMapping("/api/v1.0/lectura")
public class LecturaControlador {
    @Autowired
    LecturaServicio servicio;
    @PostMapping
    public ResponseEntity<Lectura> saveLectura(@RequestBody Lectura lectura){
        Lectura lectura1 = servicio.saveLectura(lectura);
        return new ResponseEntity(lectura1, lectura1 == null ? HttpStatus.NOT_MODIFIED : HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<String> getListLecturasPage(Pageable page){
        return new ResponseEntity(servicio.getListaLecturas(page), HttpStatus.OK);
    }
    @GetMapping("/all")
    public ResponseEntity<String> getListLecturas(){
        return new ResponseEntity(servicio.getListaLecturas(), HttpStatus.OK);
    }
}
