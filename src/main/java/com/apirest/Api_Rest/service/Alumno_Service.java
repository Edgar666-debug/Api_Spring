package com.apirest.Api_Rest.service;

import com.apirest.Api_Rest.model.Alumno;
import com.apirest.Api_Rest.repositories.IAlumno_Repository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class Alumno_Service {

    private final IAlumno_Repository alumno_Repositorio;

    public List<Alumno> getAlumnos() {
        return alumno_Repositorio.findAll();
    }

    public Alumno getAlumno(Long id) {
        return alumno_Repositorio.findById(id).orElseThrow(() ->
                new RuntimeException("Alumno no encontrado " + id));
    }

    public Alumno saveAlumno(Alumno alumno) {
        return alumno_Repositorio.save(alumno);
    }

    public List<Alumno> findByNombre (String name) {
        return alumno_Repositorio.findAll().stream().filter
                (alumno -> alumno.getNombre().equals(name)).toList();
    }

    public void deleteAlumno(Long id) {
        Alumno existAlumno = alumno_Repositorio.findById(id).orElseThrow(() ->
                new RuntimeException("Alumno no encontrado" + id));
        alumno_Repositorio.delete(existAlumno);
    }

    public Alumno updateAlumno(Long id,Alumno alumno) {
        Alumno existAlumno = alumno_Repositorio.findById(id).orElseThrow(() ->
                new RuntimeException("Alumno no encontrado " + id));
        existAlumno.setNombre(alumno.getNombre());
        existAlumno.setApellidos(alumno.getApellidos());
        existAlumno.setMatricula(alumno.getMatricula());
        return alumno_Repositorio.save(existAlumno);
    }

}
