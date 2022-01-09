package com.salesianos.triana.RealState.Recu.RealStateRecu.dto.InmobiliariaDtos;
import com.salesianos.triana.RealState.Recu.RealStateRecu.model.Vivienda;
import com.salesianos.triana.RealState.Recu.RealStateRecu.users.model.User;
import lombok.*;

import java.util.List;


@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GetInmobiliariaDto {

    private Long id;
    private String name, email, phone;
    private List<User> gestores;
    private List<Vivienda> viviendas;

}
