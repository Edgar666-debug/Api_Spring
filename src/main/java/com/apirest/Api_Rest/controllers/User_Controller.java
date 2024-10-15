package com.apirest.Api_Rest.controllers;


import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RequestMapping("/user")
@RestController
public class User_Controller {

    @PostMapping("/auth")
    public String login() {
        return "Conectado";
    }

}
