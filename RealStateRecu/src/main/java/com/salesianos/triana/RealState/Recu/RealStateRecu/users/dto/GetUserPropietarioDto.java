package com.salesianos.triana.RealState.Recu.RealStateRecu.users.dto;

import com.salesianos.triana.RealState.Recu.RealStateRecu.model.Vivienda;
import com.salesianos.triana.RealState.Recu.RealStateRecu.users.model.UserRoles;
import lombok.*;

import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class GetUserPropietarioDto {

    private UUID id;
    private String name;
    private String surnames;
    private String address;
    private String email;
    private String phone;
    private String avatar;
    private UserRoles role;
    private List<Vivienda> viviendas;

}
