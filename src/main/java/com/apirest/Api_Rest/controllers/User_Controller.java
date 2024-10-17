package com.apirest.Api_Rest.controllers;


import com.apirest.Api_Rest.model.Users;
import com.apirest.Api_Rest.service.User_Service;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RequestMapping("api/v1/user")
@RestController
public class User_Controller {

    private final User_Service user_Service;

    @GetMapping("/getById/{id}")
    public Users findbyId(@PathVariable Long id) {
        return user_Service.getById(id);
    }

    @GetMapping("/getAll")
    public List<Users> getAll() {
        return user_Service.getAll();
    }

    @PostMapping("/create")
    public Users create(@RequestBody Users user) {
        return user_Service.create(user);
    }

    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable Long id) {
        user_Service.delete(id);
    }

    @PutMapping("/update/{id}")
    public Users update(@PathVariable Long id, @RequestBody Users user) {
        return user_Service.update(id, user);
    }

}
