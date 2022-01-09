package com.salesianos.triana.RealState.Recu.RealStateRecu.dto.InteresaDtos;

import com.salesianos.triana.RealState.Recu.RealStateRecu.model.Interesa;
import com.salesianos.triana.RealState.Recu.RealStateRecu.model.Vivienda;
import com.salesianos.triana.RealState.Recu.RealStateRecu.users.model.User;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class InteresaDtoConverter {

    public Interesa createInteresaDtoToInteresa(CreateInteresaDto i, User u, Vivienda v){
        return Interesa.builder()
                .vivienda(v)
                .interesado(u)
                .createdDate(LocalDateTime.now())
                .message(i.getMensaje())
                .build();
    }

    public GetInteresaDto InteresaToGetInteresaDto(Interesa interesa){
        return GetInteresaDto.builder()
                .idInteresado(interesa.getInteresado().getId())
                .idVivienda(interesa.getVivienda().getId())
                .mensaje(interesa.getMessage())
                .createdDate(interesa.getCreatedDate())
                .build();
    }

}
