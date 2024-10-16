package com.apirest.Api_Rest.repositories;


import com.apirest.Api_Rest.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;

public interface User_Repositori  extends JpaRepository<Users, Long> {

}
