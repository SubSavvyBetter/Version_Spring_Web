package com.example.subsavvy.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UserDto {
    private String name;
    private String mail;
    private String password;
    private String profil_picture;
    private boolean admin;
}
