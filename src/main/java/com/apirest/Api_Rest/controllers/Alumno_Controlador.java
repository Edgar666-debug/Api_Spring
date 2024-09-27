package com.apirest.Api_Rest.controllers;

import com.apirest.Api_Rest.service.Alumno_Servicio;
import com.apirest.Api_Rest.model.Alumno;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@AllArgsConstructor
@RestController
@RequestMapping("/api")
public class Alumno_Controlador {

    private final Alumno_Servicio alumno_Servicio;

    @GetMapping( )
    public String index() {
        return "Conectado";
    }

    @GetMapping("/alumnos")
    public List<Alumno> listAlumnos() {
        return alumno_Servicio.getAlumnos();
    }

    @GetMapping("/alumnos/{name}")
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
