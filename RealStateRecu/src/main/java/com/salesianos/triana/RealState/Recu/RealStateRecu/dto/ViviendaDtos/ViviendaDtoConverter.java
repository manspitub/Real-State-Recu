package com.salesianos.triana.RealState.Recu.RealStateRecu.dto.ViviendaDtos;

import com.salesianos.triana.RealState.Recu.RealStateRecu.model.Vivienda;
import com.salesianos.triana.RealState.Recu.RealStateRecu.users.model.User;
import org.springframework.stereotype.Component;

@Component
public class ViviendaDtoConverter {

    public Vivienda createViviendaDtoToVivienda(CreateViviendaDto v){

        return Vivienda.builder()
                .titulo(v.getTitulo())
                .descripcion(v.getDescripcion())
                .avatar(v.getAvatar())
                .lating(v.getLating())
                .direccion(v.getDireccion())
                .codigoPostal(v.getCodigoPostal())
                .poblacion(v.getPoblacion())
                .provincia(v.getProvincia())
                .tipo(v.getTipo())
                .precio(v.getPrecio())
                .numHabitaciones(v.getNumHabitaciones())
                .metrosCuadrados(v.getMetrosCuadrados())
                .numBanios(v.getNumBanios())
                .tienePiscina(v.isTienePiscina())
                .tieneAscensor(v.isTieneAscensor())
                .tieneGaraje(v.isTieneGaraje())
                .build();
    }

    public GetViviendaDto viviendaToGetViviendaDto(Vivienda v){

        return GetViviendaDto.builder()
                .id(v.getId())
                .titulo(v.getTitulo())
                .descripcion(v.getDescripcion())
                .avatar(v.getAvatar())
                .lating(v.getLating())
                .direccion(v.getDireccion())
                .codigoPostal(v.getCodigoPostal())
                .poblacion(v.getPoblacion())
                .provincia(v.getProvincia())
                .tipo(v.getTipo())
                .precio(v.getPrecio())
                .numHabitaciones(v.getNumHabitaciones())
                .metrosCuadrados(v.getMetrosCuadrados())
                .numBanios(v.getNumBanios())
                .tienePiscina(v.isTienePiscina())
                .tieneAscensor(v.isTieneAscensor())
                .tieneGaraje(v.isTieneGaraje())
                .propietario(v.getPropietario())
                .inmo(v.getInmobiliaria())
                .build();

    }

    public GetViviendaPropietario viviendaToGetViviendaPropietarioDto(Vivienda v, User u){

        return GetViviendaPropietario.builder().
                descripcion(v.getDescripcion())
                .titulo(v.getTitulo())
                .lating(v.getLating())
                .direccion(v.getDireccion())
                .codigoPostal(v.getCodigoPostal())
                .poblacion(v.getPoblacion())
                .provincia(v.getProvincia())
                .tipo(v.getTipo())
                .precio(v.getPrecio())
                .numHabitaciones(v.getNumHabitaciones())
                .metrosCuadrados(v.getMetrosCuadrados())
                .numBanios(v.getNumBanios())
                .tienePiscina(v.isTienePiscina())
                .tieneAscensor(v.isTieneAscensor())
                .tieneGaraje(v.isTieneGaraje())
                .namePropietario(u.getName())
                .surnamesPropietario(u.getSurname())
                .emailPropietario(u.getEmail())
                .phonePropietario(u.getPhone())
                .build();

    }



}
