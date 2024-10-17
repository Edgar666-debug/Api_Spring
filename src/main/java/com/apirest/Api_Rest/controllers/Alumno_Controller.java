package com.apirest.Api_Rest.controllers;

import com.apirest.Api_Rest.service.Alumno_Service;
import com.apirest.Api_Rest.model.Alumno;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/api/v1/alumnos")
public class Alumno_Controller {

    private final Alumno_Service alumno_Servicio;

    @GetMapping( "/")
    public String index() {
        return "Conectado";
    }

    @GetMapping("")
    public List<Alumno> listAlumnos() {
        return alumno_Servicio.getAlumnos();
    }

    @GetMapping("/consultar/{name}")
    public List<Alumno> getAlumnoByName(@PathVariable String name) {
        return alumno_Servicio.findByNombre(name);
    }

    @GetMapping("/{id}")
    public Alumno getAlumno(@PathVariable Long id) {
        return alumno_Servicio.getAlumno(id);
    }

    @PostMapping
    public Alumno saveAlumno(@RequestBody Alumno alumno) {
        return alumno_Servicio.saveAlumno(alumno);
    }

    @DeleteMapping("/{id}")
    public void deleteAlumno(@PathVariable Long id) {
        alumno_Servicio.deleteAlumno(id);
    }

    @PutMapping("/{id}")
    public Alumno updateAlumno(@PathVariable Long id, @RequestBody Alumno alumno) {
        return alumno_Servicio.updateAlumno(id,alumno);
    }

}