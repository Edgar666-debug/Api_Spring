package com.apirest.Api_Rest.controllers;

import com.apirest.Api_Rest.model.Alumno;
import com.apirest.Api_Rest.repositories.Alumno_Repositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/alumno")
public class Alumno_Controlador {

    @Autowired
    private Alumno_Repositorio alumno_Repositorio;

    @GetMapping( "/alumno")
    public String index() {
        return "Conectado";
    }
    @GetMapping("/alumnos")
    public List<Alumno> getAlumnos() {
        return this.alumno_Repositorio.findAll();
    }

    @GetMapping("/{id}")
    public Alumno getAlumno(@PathVariable Long id) {
        return this.alumno_Repositorio.findById(id).orElseThrow(() ->
                new RuntimeException("Alumno no encontrado " + id));
    }

    @PostMapping
    public Alumno saveAlumno(@RequestBody Alumno alumno) {
        return this.alumno_Repositorio.save(alumno);
    }

    @DeleteMapping("/{id}")
    public String deleteAlumno(@PathVariable Long id) {
        Alumno existAlumno = this.alumno_Repositorio.findById(id).orElseThrow(() ->
                new RuntimeException("Alumno no encontrado" + id));
        this.alumno_Repositorio.delete(existAlumno);
        return "Alumno eliminado " + id + " con exito";
    }

    @PutMapping("/{id}")
    public Alumno updateAlumno(@PathVariable Long id, @RequestBody Alumno alumno) {
        Alumno existAlumno = this.alumno_Repositorio.findById(id).orElseThrow(() ->
                new RuntimeException("Alumno no encontrado " + id));
        existAlumno.setNombre(alumno.getNombre());
        existAlumno.setApellidos(alumno.getApellidos());
        existAlumno.setMatricula(alumno.getMatricula());
        return this.alumno_Repositorio.save(existAlumno);
    }

}
