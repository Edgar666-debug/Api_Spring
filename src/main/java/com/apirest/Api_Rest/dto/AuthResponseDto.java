package com.apirest.Api_Rest.dto;

import lombok.Data;

@Data
public class AuthResponseDto {

    private String token;
    private String refreshToken;
}
