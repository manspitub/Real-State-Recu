package com.salesianos.triana.RealState.Recu.RealStateRecu.users.dto;

import com.salesianos.triana.RealState.Recu.RealStateRecu.users.model.UserRoles;
import lombok.*;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter
@Builder

public class GetUserDto {

    private UUID id;
    private String name;
    private String email;
    private String avatar;
    private UserRoles role;
}
