package com.salesianos.triana.RealState.Recu.RealStateRecu.security.dto;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class JwtUserResponse {

    private String email;
    private String name;
    private String surnames;
    private String avatar;
    private String phone;
    private String role;
    private String token;


}
