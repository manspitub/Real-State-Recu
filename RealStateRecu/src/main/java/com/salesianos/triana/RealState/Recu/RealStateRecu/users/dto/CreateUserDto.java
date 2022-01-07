package com.salesianos.triana.RealState.Recu.RealStateRecu.users.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class CreateUserDto {

    private String name;
    private String surnames;
    private String adress;
    private String email;
    private String phone;
    private String avatar;
    private String password;
    private String password2;

}
