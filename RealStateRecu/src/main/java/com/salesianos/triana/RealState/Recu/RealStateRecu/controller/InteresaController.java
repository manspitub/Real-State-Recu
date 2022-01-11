package com.salesianos.triana.RealState.Recu.RealStateRecu.controller;

import com.salesianos.triana.RealState.Recu.RealStateRecu.dto.InteresaDtos.CreateInteresaDto;
import com.salesianos.triana.RealState.Recu.RealStateRecu.dto.InteresaDtos.GetInteresaDto;
import com.salesianos.triana.RealState.Recu.RealStateRecu.dto.InteresaDtos.InteresaDtoConverter;
import com.salesianos.triana.RealState.Recu.RealStateRecu.dto.ViviendaDtos.GetViviendaDto;
import com.salesianos.triana.RealState.Recu.RealStateRecu.model.Interesa;
import com.salesianos.triana.RealState.Recu.RealStateRecu.model.Vivienda;
import com.salesianos.triana.RealState.Recu.RealStateRecu.repos.InteresaRepository;
import com.salesianos.triana.RealState.Recu.RealStateRecu.repos.ViviendaRepository;
import com.salesianos.triana.RealState.Recu.RealStateRecu.services.InteresaService;
import com.salesianos.triana.RealState.Recu.RealStateRecu.services.ViviendaService;
import com.salesianos.triana.RealState.Recu.RealStateRecu.users.dto.GetUserDto;
import com.salesianos.triana.RealState.Recu.RealStateRecu.users.dto.UserDtoConverter;
import com.salesianos.triana.RealState.Recu.RealStateRecu.users.model.User;
import com.salesianos.triana.RealState.Recu.RealStateRecu.users.model.UserRoles;
import com.salesianos.triana.RealState.Recu.RealStateRecu.users.repo.UserRepository;
import com.salesianos.triana.RealState.Recu.RealStateRecu.users.services.UserServices;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequiredArgsConstructor
public class InteresaController {

    private final InteresaRepository repository;
    private final ViviendaRepository viviendaRepository;
    private final InteresaDtoConverter dtoConverter;
    private final UserDtoConverter userDtoConverter;
    private final UserRepository userRepository;
    private final InteresaService service;

    @PostMapping("vivienda/{id}/meinteresa")
    public ResponseEntity<GetInteresaDto> createInteresa (@RequestBody CreateInteresaDto interesaDto, @PathVariable Long id, @AuthenticationPrincipal User user) {

        Optional<Vivienda> vivienda = viviendaRepository.findById(id);
        if (vivienda.isPresent()){
            if (user.getRoles().equals(UserRoles.PROPIETARIO)){
                Interesa interesa = dtoConverter.createInteresaDtoToInteresa(interesaDto, user, vivienda.get());
                interesa.addInteresado(user);
                interesa.addVivienda(vivienda.get());

                repository.save(interesa);
                user.getInteresas().add(interesa);

                userRepository.save(user);

                return ResponseEntity.status(HttpStatus.CREATED).body(dtoConverter.InteresaToGetInteresaDto(interesa));
            } else {
                return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
            }
        } else {
            return ResponseEntity.notFound().build();
        }

    }

    @DeleteMapping("vivienda/{id}/meinteresa")
    public ResponseEntity<?> deleteInteresa(@PathVariable Long id, @AuthenticationPrincipal User user){

        Optional<Vivienda> viviendaABuscar = viviendaRepository.findById(id);

        if (viviendaABuscar.isPresent()){
            List<Interesa> interesas = viviendaABuscar.get().getInteresas();

            for (Interesa in : interesas) {
                if (in.getInteresado().getId().equals(user.getId()) || user.getRoles().equals(UserRoles.ADMIN)){
                    repository.delete(in);
                }
            }
        }
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/interesado")
    public ResponseEntity<List<User>> getInteresados(){

        List<User> interesados = userRepository.findByRole(UserRoles.PROPIETARIO);

        return ResponseEntity.ok().body(interesados);

    }

    @GetMapping("interesado/{id}")
    public ResponseEntity<GetUserDto> findInteresado(@PathVariable UUID id, @AuthenticationPrincipal User user){

        Optional<User> interesadoABuscar = userRepository.findById(id);

        if (interesadoABuscar.isPresent()){
            if (interesadoABuscar.get().getId().equals(user.getId()) || user.getRoles().equals(UserRoles.ADMIN)){
                return ResponseEntity.ok(userDtoConverter.userToGetUserDto(interesadoABuscar.get()));
            }else {
                return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
            }

        }
        else {
            return ResponseEntity.notFound().build();
        }


    }

    public ResponseEntity<List<GetViviendaDto>> viviendasTopInteresas(){
        return ResponseEntity.ok(service.topViviendaDto());
    }


    }
