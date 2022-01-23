package com.salesianos.triana.RealState.Recu.RealStateRecu.data;

import com.salesianos.triana.RealState.Recu.RealStateRecu.dto.InmobiliariaDtos.CreateInmobiliariaDto;
import com.salesianos.triana.RealState.Recu.RealStateRecu.dto.InteresaDtos.CreateInteresaDto;
import com.salesianos.triana.RealState.Recu.RealStateRecu.dto.InteresaDtos.InteresaDtoConverter;
import com.salesianos.triana.RealState.Recu.RealStateRecu.model.Inmobiliaria;
import com.salesianos.triana.RealState.Recu.RealStateRecu.model.Interesa;
import com.salesianos.triana.RealState.Recu.RealStateRecu.model.Vivienda;
import com.salesianos.triana.RealState.Recu.RealStateRecu.services.InmoService;
import com.salesianos.triana.RealState.Recu.RealStateRecu.services.InteresaService;
import com.salesianos.triana.RealState.Recu.RealStateRecu.services.ViviendaService;
import com.salesianos.triana.RealState.Recu.RealStateRecu.users.dto.CreateUserDto;
import com.salesianos.triana.RealState.Recu.RealStateRecu.users.dto.CreateUserGestorDto;
import com.salesianos.triana.RealState.Recu.RealStateRecu.users.dto.UserDtoConverter;
import com.salesianos.triana.RealState.Recu.RealStateRecu.users.model.User;
import com.salesianos.triana.RealState.Recu.RealStateRecu.users.services.UserServices;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;

import javax.annotation.PostConstruct;

@RequiredArgsConstructor
@Component
public class InitData {

    private final InteresaDtoConverter interesaDtoConverter;
    private final InmoService inmoService;
    private final UserServices userServices;
    private final InteresaService interesaService;
    private final ViviendaService viviendaService;
    private final UserDtoConverter userDtoConverter;

    @PostConstruct
    public void data(){

        //Admin de inicio

        CreateUserDto user = CreateUserDto.builder()
                .name("Manuel")
                .surnames("Spinola")
                .adress("calle luismi lopez magaña")
                .email("manspitub5@hotmail.com")
                .phone("679640570")
                .avatar("https://hips.hearstapps.com/hmg-prod.s3.amazonaws.com/images/avatar-2-1583234102.jpg")
                .password("holaluismi")
                .password2("holaluismi")
                .build();
        userServices.saveAdmin(user);

        //Propietarios

        User prop1 = userServices.saveProp(CreateUserDto.builder().name("Pepe").surnames("Pérez").phone("54365367").adress("calle condes de bustillo").avatar("https://i.pinimg.com/originals/5f/13/93/5f1393ca14737a002c186b32f176123b.jpg").email("manspitub6@hotmail.com").password("holaluismi").password2("holaluismi").build());
        User prop2 = userServices.saveProp(CreateUserDto.builder().name("Manuel").surnames("Sanchez").phone("54365367").adress("calle condes de bustillo").avatar("https://i.pinimg.com/originals/5f/13/93/5f1393ca14737a002c186b32f176123b.jpg").email("manspitub67@hotmail.com").password("holaluismi").password2("holaluismi").build());
        User prop3 = userServices.saveProp(CreateUserDto.builder().name("Francisco").surnames("Godoy").phone("54365367").adress("calle condes de bustillo").avatar("https://i.pinimg.com/originals/5f/13/93/5f1393ca14737a002c186b32f176123b.jpg").email("manspitub7@hotmail.com").password("holaluismi").password2("holaluismi").build());
        User prop4 = userServices.saveProp(CreateUserDto.builder().name("Sara").surnames("Garcia").phone("54365367").adress("calle condes de bustillo").avatar("https://i.pinimg.com/originals/5f/13/93/5f1393ca14737a002c186b32f176123b.jpg").email("manspitub8@hotmail.com").password("holaluismi").password2("holaluismi").build());
        User prop5 = userServices.saveProp(CreateUserDto.builder().name("Javier").surnames("Fernandez").phone("54365367").adress("calle condes de bustillo").avatar("https://i.pinimg.com/originals/5f/13/93/5f1393ca14737a002c186b32f176123b.jpg").email("manspitub9@hotmail.com").password("holaluismi").password2("holaluismi").build());
        User prop6 = userServices.saveProp(CreateUserDto.builder().name("Marcos").surnames("Asensio").phone("54365367").adress("calle condes de bustillo").avatar("https://i.pinimg.com/originals/5f/13/93/5f1393ca14737a002c186b32f176123b.jpg").email("manspitub10@hotmail.com").password("holaluismi").password2("holaluismi").build());
        User prop7 = userServices.saveProp(CreateUserDto.builder().name("Jose").surnames("Giménez").phone("54365367").adress("calle condes de bustillo").avatar("https://i.pinimg.com/originals/5f/13/93/5f1393ca14737a002c186b32f176123b.jpg").email("manspitub11@hotmail.com").password("holaluismi").password2("holaluismi").build());
        User prop8 = userServices.saveProp(CreateUserDto.builder().name("Mario").surnames("Hermoso").phone("54365367").adress("calle condes de bustillo").avatar("https://i.pinimg.com/originals/5f/13/93/5f1393ca14737a002c186b32f176123b.jpg").email("manspitub12@hotmail.com").password("holaluismi").password2("holaluismi").build());
        User prop9 = userServices.saveProp(CreateUserDto.builder().name("Jorge").surnames("Resurreccion").phone("54365367").adress("calle condes de bustillo").avatar("https://i.pinimg.com/originals/5f/13/93/5f1393ca14737a002c186b32f176123b.jpg").email("manspitub15@hotmail.com").password("holaluismi").password2("holaluismi").build());
        User prop10 = userServices.saveProp(CreateUserDto.builder().name("Jan").surnames("Oblak").phone("54365367").adress("calle condes de bustillo").avatar("https://i.pinimg.com/originals/5f/13/93/5f1393ca14737a002c186b32f176123b.jpg").email("manspitub25@hotmail.com").password("holaluismi").password2("holaluismi").build());

        //Inmobiliarias

        Inmobiliaria inmo1 = inmoService.save(Inmobiliaria.builder().name("idealista").email("idealista@idealista.com").phone("124325676").build());

        inmoService.save(inmo1);

        Inmobiliaria inmo2 = inmoService.save(Inmobiliaria.builder().name("Neinor Homes").email("NeinorHomes@NeinorHomes.com").phone("124325676").build());

        inmoService.save(inmo2);

        Inmobiliaria inmo3 = inmoService.save(Inmobiliaria.builder().name("Vía Célere").email("VíaCélere@VíaCélere.com").phone("124325676").build());

        inmoService.save(inmo3);

        Inmobiliaria inmo4 = inmoService.save(Inmobiliaria.builder().name("Metrovacesa").email("Metrovacesa@Metrovacesa.com").phone("124325676").build());

        inmoService.save(inmo4);

        Inmobiliaria inmo5 = inmoService.save(Inmobiliaria.builder().name("Aedas Homes").email("AedasHomes@AedasHomes.com").phone("124325676").build());

        inmoService.save(inmo5);

        Inmobiliaria inmo6 = inmoService.save(Inmobiliaria.builder().name("Habitat Inmobiliaria").email("HabitatInmobiliaria@HabitatInmobiliaria.com").phone("124325676").build());

        inmoService.save(inmo6);

        Inmobiliaria inmo7 = inmoService.save(Inmobiliaria.builder().name("Amenavar").email("amenavar@amenavar.com").phone("124325676").build());

        inmoService.save(inmo7);

        Inmobiliaria inmo8 = inmoService.save(Inmobiliaria.builder().name("ASG Homes").email("ASGHomes@ASGHomes.com").phone("124325676").build());

        inmoService.save(inmo8);


        Inmobiliaria inmo9 = inmoService.save(Inmobiliaria.builder().name("Pryconsa").email("Pryconsa@Pryconsa.com").phone("124325676").build());

        inmoService.save(inmo9);

        Inmobiliaria inmo10 = inmoService.save(Inmobiliaria.builder().name("Insur").email("Insur@Insur.com").phone("124325676").build());

        inmoService.save(inmo10);


        //Viviendas

        Vivienda vivienda1 = Vivienda.builder().titulo("casita wapa").descripcion("esto es una casita wapa").avatar("https://www.siberzone.es/blog-sistemas-ventilacion/wp-content/uploads/2016/12/vivienda-unifamiliar-1024x680.jpg").lating("-17.6565, -61.7160").precio(1000000.0).tipo("compra").direccion("Bolivia").codigoPostal("41010").poblacion("Bolivia")
                .provincia("Bolivia").numHabitaciones(4).numBanios(2).metrosCuadrados(40.0).tienePiscina(true).tieneGaraje(false).tieneAscensor(true).build();

        viviendaService.save(vivienda1);
        vivienda1.addPropietario(prop1);
        vivienda1.addInmobiliaria(inmo1);

        Vivienda vivienda2 = Vivienda.builder().titulo("casita chula").descripcion("esto es una casita wapa").avatar("https://www.siberzone.es/blog-sistemas-ventilacion/wp-content/uploads/2016/12/vivienda-unifamiliar-1024x680.jpg").lating("-17.6565, -61.7160").precio(1000000.0).tipo("compra").direccion("Bolivia").codigoPostal("41010").poblacion("Bolivia")
                .provincia("Bolivia").numHabitaciones(4).numBanios(2).metrosCuadrados(40.0).tienePiscina(true).tieneGaraje(false).tieneAscensor(true).build();

        viviendaService.save(vivienda2);
        vivienda1.addPropietario(prop2);
        vivienda1.addInmobiliaria(inmo2);

        Vivienda vivienda3 = Vivienda.builder().titulo("casita fea").descripcion("esto es una casita fea").avatar("https://www.siberzone.es/blog-sistemas-ventilacion/wp-content/uploads/2016/12/vivienda-unifamiliar-1024x680.jpg").lating("-17.6565, -61.7160").precio(1000000.0).tipo("compra").direccion("Bolivia").codigoPostal("41010").poblacion("Bolivia")
                .provincia("Bolivia").numHabitaciones(4).numBanios(2).metrosCuadrados(40.0).tienePiscina(true).tieneGaraje(false).tieneAscensor(true).build();

        viviendaService.save(vivienda3);
        vivienda1.addPropietario(prop3);
        vivienda1.addInmobiliaria(inmo3);

        Vivienda vivienda4 = Vivienda.builder().titulo("casita wapa").descripcion("esto es una casita wapa").avatar("https://www.siberzone.es/blog-sistemas-ventilacion/wp-content/uploads/2016/12/vivienda-unifamiliar-1024x680.jpg").lating("-17.6565, -61.7160").precio(1000000.0).tipo("compra").direccion("Bolivia").codigoPostal("41010").poblacion("Bolivia")
                .provincia("Bolivia").numHabitaciones(4).numBanios(2).metrosCuadrados(40.0).tienePiscina(true).tieneGaraje(false).tieneAscensor(true).build();

        viviendaService.save(vivienda4);
        vivienda1.addPropietario(prop4);
        vivienda1.addInmobiliaria(inmo4);


        Vivienda vivienda5 = Vivienda.builder().titulo("casita wapa").descripcion("esto es una casita wapa").avatar("https://www.siberzone.es/blog-sistemas-ventilacion/wp-content/uploads/2016/12/vivienda-unifamiliar-1024x680.jpg").lating("-17.6565, -61.7160").precio(1000000.0).tipo("compra").direccion("Bolivia").codigoPostal("41010").poblacion("Bolivia")
                .provincia("Bolivia").numHabitaciones(4).numBanios(2).metrosCuadrados(40.0).tienePiscina(true).tieneGaraje(false).tieneAscensor(true).build();

        viviendaService.save(vivienda5);
        vivienda1.addPropietario(prop5);
        vivienda1.addInmobiliaria(inmo5);

        Vivienda vivienda6 = Vivienda.builder().titulo("casita wapa").descripcion("esto es una casita wapa").avatar("https://www.siberzone.es/blog-sistemas-ventilacion/wp-content/uploads/2016/12/vivienda-unifamiliar-1024x680.jpg").lating("-17.6565, -61.7160").precio(1000000.0).tipo("compra").direccion("Bolivia").codigoPostal("41010").poblacion("Bolivia")
                .provincia("Bolivia").numHabitaciones(4).numBanios(2).metrosCuadrados(40.0).tienePiscina(true).tieneGaraje(false).tieneAscensor(true).build();

        viviendaService.save(vivienda6);
        vivienda1.addPropietario(prop6);
        vivienda1.addInmobiliaria(inmo6);

        Vivienda vivienda7 = Vivienda.builder().titulo("casita wapa").descripcion("esto es una casita wapa").avatar("https://www.siberzone.es/blog-sistemas-ventilacion/wp-content/uploads/2016/12/vivienda-unifamiliar-1024x680.jpg").lating("-17.6565, -61.7160").precio(1000000.0).tipo("compra").direccion("Bolivia").codigoPostal("41010").poblacion("Bolivia")
                .provincia("Bolivia").numHabitaciones(4).numBanios(2).metrosCuadrados(40.0).tienePiscina(true).tieneGaraje(false).tieneAscensor(true).build();

        viviendaService.save(vivienda7);
        vivienda1.addPropietario(prop7);
        vivienda1.addInmobiliaria(inmo7);

        Vivienda vivienda8 = Vivienda.builder().titulo("casita wapa").descripcion("esto es una casita wapa").avatar("https://www.siberzone.es/blog-sistemas-ventilacion/wp-content/uploads/2016/12/vivienda-unifamiliar-1024x680.jpg").lating("-17.6565, -61.7160").precio(1000000.0).tipo("compra").direccion("Bolivia").codigoPostal("41010").poblacion("Bolivia")
                .provincia("Bolivia").numHabitaciones(4).numBanios(2).metrosCuadrados(40.0).tienePiscina(true).tieneGaraje(false).tieneAscensor(true).build();

        viviendaService.save(vivienda8);
        vivienda1.addPropietario(prop8);
        vivienda1.addInmobiliaria(inmo8);

        Vivienda vivienda9 = Vivienda.builder().titulo("casita wapa").descripcion("esto es una casita wapa").avatar("https://www.siberzone.es/blog-sistemas-ventilacion/wp-content/uploads/2016/12/vivienda-unifamiliar-1024x680.jpg").lating("-17.6565, -61.7160").precio(1000000.0).tipo("compra").direccion("Bolivia").codigoPostal("41010").poblacion("Bolivia")
                .provincia("Bolivia").numHabitaciones(4).numBanios(2).metrosCuadrados(40.0).tienePiscina(true).tieneGaraje(false).tieneAscensor(true).build();


        viviendaService.save(vivienda9);
        vivienda1.addPropietario(prop9);
        vivienda1.addInmobiliaria(inmo9);

        Vivienda vivienda10 = Vivienda.builder().titulo("casita wapa").descripcion("esto es una casita wapa").avatar("https://www.siberzone.es/blog-sistemas-ventilacion/wp-content/uploads/2016/12/vivienda-unifamiliar-1024x680.jpg").lating("-17.6565, -61.7160").precio(1000000.0).tipo("compra").direccion("Bolivia").codigoPostal("41010").poblacion("Bolivia")
                .provincia("Bolivia").numHabitaciones(4).numBanios(2).metrosCuadrados(40.0).tienePiscina(true).tieneGaraje(false).tieneAscensor(true).build();

        viviendaService.save(vivienda10);
        vivienda1.addPropietario(prop10);
        vivienda1.addInmobiliaria(inmo10);



        Interesa interesa1 = interesaService.save(interesaDtoConverter.createInteresaDtoToInteresa(CreateInteresaDto.builder().mensaje("Bonita casa").build(),prop1,vivienda3));
        Interesa interesa2 = interesaService.save(interesaDtoConverter.createInteresaDtoToInteresa(CreateInteresaDto.builder().mensaje("Fantastico cuarto de baño y retrete").build(),prop2,vivienda6));
        Interesa interesa3 = interesaService.save(interesaDtoConverter.createInteresaDtoToInteresa(CreateInteresaDto.builder().mensaje("Esta casa es una maravilla").build(),prop3,vivienda7));


        User gestor1 = userServices.saveGestor(CreateUserGestorDto.builder().name("Jose juan").surnames("perez").email("josejuian1@hotmail.com").phone("4328794562").avatar("https://media.revistagq.com/photos/5f45010acb266484bb785c78/master/pass/dragon-ball-z.jpg").password("holaluismi").password2("holaluismi").idInmobiliaria(inmo1.getId()).build());
        User gestor2 = userServices.saveGestor(CreateUserGestorDto.builder().name("Jose juan").surnames("perez").email("josejuian2@hotmail.com").phone("4328794562").avatar("https://media.revistagq.com/photos/5f45010acb266484bb785c78/master/pass/dragon-ball-z.jpg").password("holaluismi").password2("holaluismi").idInmobiliaria(inmo2.getId()).build());
        User gestor3 = userServices.saveGestor(CreateUserGestorDto.builder().name("Jose juan").surnames("perez").email("josejuian3@hotmail.com").phone("4328794562").avatar("https://media.revistagq.com/photos/5f45010acb266484bb785c78/master/pass/dragon-ball-z.jpg").password("holaluismi").password2("holaluismi").idInmobiliaria(inmo3.getId()).build());
        User gestor4 = userServices.saveGestor(CreateUserGestorDto.builder().name("Jose juan").surnames("perez").email("josejuian4@hotmail.com").phone("4328794562").avatar("https://media.revistagq.com/photos/5f45010acb266484bb785c78/master/pass/dragon-ball-z.jpg").password("holaluismi").password2("holaluismi").idInmobiliaria(inmo4.getId()).build());
        User gestor5 = userServices.saveGestor(CreateUserGestorDto.builder().name("Jose juan").surnames("perez").email("josejuian5@hotmail.com").phone("4328794562").avatar("https://media.revistagq.com/photos/5f45010acb266484bb785c78/master/pass/dragon-ball-z.jpg").password("holaluismi").password2("holaluismi").idInmobiliaria(inmo5.getId()).build());
    }


}
