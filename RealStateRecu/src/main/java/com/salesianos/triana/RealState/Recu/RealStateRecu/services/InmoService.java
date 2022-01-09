package com.salesianos.triana.RealState.Recu.RealStateRecu.services;

import com.salesianos.triana.RealState.Recu.RealStateRecu.dto.InmobiliariaDtos.GetInmobiliariaDto;
import com.salesianos.triana.RealState.Recu.RealStateRecu.dto.InmobiliariaDtos.InmobiliariaDtoConverter;
import com.salesianos.triana.RealState.Recu.RealStateRecu.model.Inmobiliaria;
import com.salesianos.triana.RealState.Recu.RealStateRecu.users.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service("inmoService")
@RequiredArgsConstructor
public class InmoService {

    @Autowired
    private final InmobiliariaDtoConverter dtoConverter;

    public List<GetInmobiliariaDto> inmoToGetInmoDto(List<Inmobiliaria> inmos){
        List<GetInmobiliariaDto> inmobiliarias = new ArrayList<>();

        inmos.stream().forEach(i -> {
            inmobiliarias.add(dtoConverter.inmobiliariaToGetInmobiliariaDto(i));
        });

        return inmobiliarias;
    }

    public boolean checkGestorBelongsToInmo(User g, Inmobiliaria inmo){

        boolean belongs = false;

        for (User gestor: inmo.getGestores()){
            if (gestor.getId().equals(g.getId())) {
                belongs = true;
                break;
            }

        }
        return belongs;
    }

}
