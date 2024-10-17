package com.apirest.Api_Rest.controllers;


import com.apirest.Api_Rest.dto.AuthRequetsDto;
import com.apirest.Api_Rest.service.JwtUtilService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
@RequestMapping("api/v1/auth")
public class Auth_Controller {

    private AuthenticationManager authenticationManager;
    private UserDetailsService userDetailsService;
    private JwtUtilService jwtUtilService;

    @PostMapping("/login")
    public ResponseEntity<?> auth(@RequestBody AuthRequetsDto authRequest) {
    try {
        this.authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                        authRequest.getEmail(), authRequest.getPassword()
                ));

        UserDetails userDetails = this.userDetailsService.loadUserByUsername(
                authRequest.getEmail());

        String token = this.jwtUtilService.generateToken(userDetails);

        return new ResponseEntity<>(token, HttpStatus.OK);

        } catch (Exception e) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                .body("Error de autenticación: " + e.getMessage());
    }

    }

}
