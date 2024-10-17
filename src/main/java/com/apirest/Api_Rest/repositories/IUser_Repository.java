package com.apirest.Api_Rest.repositories;


import com.apirest.Api_Rest.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;



public interface IUser_Repository extends JpaRepository<Users, Long> {

    Users findByEmail(String email);
}
