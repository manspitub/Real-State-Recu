package com.salesianos.triana.RealState.Recu.RealStateRecu.dto.InmobiliariaDtos;

import com.salesianos.triana.RealState.Recu.RealStateRecu.model.Inmobiliaria;
import com.salesianos.triana.RealState.Recu.RealStateRecu.users.model.User;
import com.salesianos.triana.RealState.Recu.RealStateRecu.users.model.UserRoles;
import org.springframework.stereotype.Component;

@Component
public class InmobiliariaDtoConverter {

    public Inmobiliaria createInmobiliariaDtoToInmobiliaria(CreateInmobiliariaDto i){
        return Inmobiliaria.builder()
                .name(i.getName())
                .email(i.getEmail())
                .phone(i.getPhone())
                .build();
    }

    public GetInmobiliariaDto inmobiliariaToGetInmobiliariaDto(Inmobiliaria i){
        return GetInmobiliariaDto.builder()
                .id(i.getId())
                .name(i.getName())
                .phone(i.getPhone())
                .email(i.getEmail())
                .viviendas(i.getViviendas())
                .build();

    }

    public User createGestorInmoDtoToGestor(CreateGestorInmoDto g, Inmobiliaria i){

        return User.builder()
                .name(g.getName())
                .surname(g.getSurnames())
                .adress(g.getAdress())
                .avatar(g.getAvatar())
                .phone(g.getPhone())
                .password(g.getPassword())
                .roles(UserRoles.GESTOR)
                .inmobiliaria(i)
                .build();
    }

}
