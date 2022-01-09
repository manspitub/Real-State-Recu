package com.salesianos.triana.RealState.Recu.RealStateRecu.users.dto;


import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CreateUserGestorDto {

    private String name;
    private String surnames;
    private String address;
    private String email;
    private String phone;
    private String avatar;
    private String password;
    private String password2;
    private Long idInmobiliaria;

}
