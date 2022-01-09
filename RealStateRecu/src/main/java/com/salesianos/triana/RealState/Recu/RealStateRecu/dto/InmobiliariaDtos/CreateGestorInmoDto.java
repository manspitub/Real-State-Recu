package com.salesianos.triana.RealState.Recu.RealStateRecu.dto.InmobiliariaDtos;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CreateGestorInmoDto {

    private String name;
    private String surnames;
    private String adress;
    private String email;
    private String phone;
    private String avatar;
    private String password;
    private String password2;


}
