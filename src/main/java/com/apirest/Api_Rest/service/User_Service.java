package com.apirest.Api_Rest.service;


import com.apirest.Api_Rest.model.User;
import com.apirest.Api_Rest.repositories.User_Repositori;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class User_Service {

    public final User_Repositori user_Repositorio;

}
