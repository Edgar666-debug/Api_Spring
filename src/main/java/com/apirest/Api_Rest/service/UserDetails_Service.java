package com.apirest.Api_Rest.service;


import com.apirest.Api_Rest.model.Users;
import com.apirest.Api_Rest.repositories.IUser_Repository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
@AllArgsConstructor
public class UserDetails_Service implements UserDetailsService {

    private IUser_Repository user_Repositorio;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Users user = this.user_Repositorio.findByEmail(username);
        if (user == null) {
            throw new UsernameNotFoundException(username);
        } else {
            return new User(user.getEmail(), user.getPassword(), new ArrayList<>());
        }
    }
}
