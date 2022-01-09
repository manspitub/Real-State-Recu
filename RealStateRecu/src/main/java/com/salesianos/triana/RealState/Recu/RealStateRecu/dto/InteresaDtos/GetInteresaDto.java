package com.salesianos.triana.RealState.Recu.RealStateRecu.dto.InteresaDtos;

import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder

public class GetInteresaDto {

    private UUID idInteresado;
    private Long idVivienda;
    private String mensaje;
    private LocalDateTime createdDate;

}
