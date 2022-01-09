package com.salesianos.triana.RealState.Recu.RealStateRecu.users.dto;

import com.salesianos.triana.RealState.Recu.RealStateRecu.users.model.UserRoles;
import lombok.*;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GetUserGestorDto {

    private UUID id;
    private String name;
    private String surnames;
    private String address;
    private String email;
    private String phone;
    private String avatar;
    private UserRoles role;

}
