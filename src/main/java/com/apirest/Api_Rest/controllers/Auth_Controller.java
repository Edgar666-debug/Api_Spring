package com.apirest.Api_Rest.controllers;


import com.apirest.Api_Rest.dto.AuthRequetsDto;
import com.apirest.Api_Rest.dto.AuthResponseDto;
import com.apirest.Api_Rest.model.Users;
import com.apirest.Api_Rest.repositories.IUser_Repository;
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

import java.util.Map;

@AllArgsConstructor
@RestController
@RequestMapping("api/v1/auth")
public class Auth_Controller {

    private AuthenticationManager authenticationManager;
    private UserDetailsService userDetailsService;
    private IUser_Repository users_Repository;
    private JwtUtilService jwtUtilService;

    @PostMapping("/login")
    public ResponseEntity<?> auth(@RequestBody AuthRequetsDto authRequest) {
    try {
        this.authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                        authRequest.getEmail(), authRequest.getPassword()
                ));

        UserDetails userDetails = this.userDetailsService.loadUserByUsername(
                authRequest.getEmail());

        Users user = users_Repository.findByEmail(authRequest.getEmail());

        String token = this.jwtUtilService.generateToken(userDetails, user.getRol());
        String refreshToken = this.jwtUtilService.generateRefreshToken(userDetails, user.getRol());

        AuthResponseDto authResponse = new AuthResponseDto();
        authResponse.setToken(token);
        authResponse.setRefreshToken(refreshToken);

        return new ResponseEntity<AuthResponseDto>(authResponse, HttpStatus.OK);

        } catch (Exception e) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                .body("Error de autenticación: " + e.getMessage());
    }

    }


    @PostMapping("/refresh")
    public ResponseEntity<?> refreshToken(@RequestBody Map<String, String> request) {
        String refreshToken = request.get("refreshToken");
        try{
            String username = jwtUtilService.extractUsername(refreshToken);
            UserDetails userDetails = userDetailsService.loadUserByUsername(username);
            Users userModel = users_Repository.findByEmail(username);

            if (jwtUtilService.validateToken(refreshToken, userDetails)) {
                String newtoken = jwtUtilService.generateToken(userDetails, userModel.getRol());
                String newRefreshToken = jwtUtilService.generateRefreshToken(userDetails, userModel.getRol());

                AuthResponseDto authResponse = new AuthResponseDto();
                authResponse.setToken(newtoken);
                authResponse.setRefreshToken(newRefreshToken);

                return new ResponseEntity<AuthResponseDto>(authResponse, HttpStatus.OK);
            } else {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Refresh token no válido");
            }

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body("Error refresh token: " + e.getMessage());
        }
    }

}
