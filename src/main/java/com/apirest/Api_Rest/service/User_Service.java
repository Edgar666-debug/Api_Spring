package com.apirest.Api_Rest.service;

import com.apirest.Api_Rest.model.Users;
import com.apirest.Api_Rest.repositories.IUser_Repository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;


@AllArgsConstructor
@Service
public class User_Service {

    private final IUser_Repository users_Repository;

    public Users create(Users user) {
        return users_Repository.save(user);
    }

    public Users update(Long id,Users user) {
        Users existingUser = users_Repository.findById(id).orElseThrow(
                () -> new RuntimeException("Ususario no encontrado" + user.getName()));
        existingUser.setEmail(user.getEmail());
        existingUser.setName(user.getName());
        existingUser.setPassword(user.getPassword());
        existingUser.setRol(user.getRol());
        return users_Repository.save(existingUser);
    }

    public Users delete(Long id) {
        Users existingUser = users_Repository.findById(id).orElseThrow(
                () -> new RuntimeException("Ususario no encontrado" + id));
        users_Repository.delete(existingUser);
        return existingUser;
    }

    public Users getById(Long id) {
        return users_Repository.findById(id).orElseThrow(
                () -> new RuntimeException("Ususario no encontrado" + id));
    }

    public List<Users> getAll() {
        return users_Repository.findAll();
    }

}
