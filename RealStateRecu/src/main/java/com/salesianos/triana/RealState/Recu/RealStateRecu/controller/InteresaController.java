package com.salesianos.triana.RealState.Recu.RealStateRecu.controller;

import com.salesianos.triana.RealState.Recu.RealStateRecu.dto.InteresaDtos.CreateInteresaDto;
import com.salesianos.triana.RealState.Recu.RealStateRecu.dto.InteresaDtos.GetInteresaDto;
import com.salesianos.triana.RealState.Recu.RealStateRecu.dto.InteresaDtos.InteresaDtoConverter;
import com.salesianos.triana.RealState.Recu.RealStateRecu.model.Interesa;
import com.salesianos.triana.RealState.Recu.RealStateRecu.model.Vivienda;
import com.salesianos.triana.RealState.Recu.RealStateRecu.repos.InteresaRepository;
import com.salesianos.triana.RealState.Recu.RealStateRecu.repos.ViviendaRepository;
import com.salesianos.triana.RealState.Recu.RealStateRecu.users.model.User;
import com.salesianos.triana.RealState.Recu.RealStateRecu.users.model.UserRoles;
import com.salesianos.triana.RealState.Recu.RealStateRecu.users.repo.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
public class InteresaController {

    private final InteresaRepository repository;
    private final ViviendaRepository viviendaRepository;
    private final InteresaDtoConverter dtoConverter;
    private final UserRepository userRepository;

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


    }
