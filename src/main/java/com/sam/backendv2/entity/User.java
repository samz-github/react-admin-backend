package com.sam.backendv2.entity;
import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@Component
public class User {
   private int id;
   private String username;
   private String password;
}
