package com.apirest.Api_Rest.service;

import com.apirest.Api_Rest.model.Alumno;
import com.apirest.Api_Rest.repositories.Alumno_Repositorio;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.List;

@AllArgsConstructor
@Service
public class Alumno_Servicio {

    private final Alumno_Repositorio alumno_Repositorio;

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
