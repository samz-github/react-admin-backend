package com.sam.backendv2.entity;
import lombok.Data;

@Data
public class AuthenticationResponse {
    private String username;
    private String roleName;
    private String token;
    private String status;

    public AuthenticationResponse(String username, String roleName, String token, String status) {
        this.username = username;
        this.roleName = roleName;
        this.token = token;
        this.status = status;
    }
}
