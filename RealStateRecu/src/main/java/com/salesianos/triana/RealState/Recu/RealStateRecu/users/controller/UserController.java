package com.salesianos.triana.RealState.Recu.RealStateRecu.users.controller;

import com.salesianos.triana.RealState.Recu.RealStateRecu.security.dto.LoginDto;
import com.salesianos.triana.RealState.Recu.RealStateRecu.services.ViviendaService;
import com.salesianos.triana.RealState.Recu.RealStateRecu.users.dto.*;
import com.salesianos.triana.RealState.Recu.RealStateRecu.users.model.User;
import com.salesianos.triana.RealState.Recu.RealStateRecu.users.model.UserRoles;
import com.salesianos.triana.RealState.Recu.RealStateRecu.users.repo.UserRepository;
import com.salesianos.triana.RealState.Recu.RealStateRecu.users.services.UserServices;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
public class UserController {

    private UserServices services;
    private UserDtoConverter converter;
    private UserRepository repo;
    private ViviendaService viviendaService;
    private GetUserPropietarioDto userPropietarioDto;

    @PostMapping("auth/register/user")
    public ResponseEntity<GetUserDto> newPropietario(@RequestBody CreateUserDto nuevoUser){

        User prop = services.saveProp(nuevoUser);

        if (prop == null){
            return ResponseEntity.badRequest().build();
        }
        else {
            return ResponseEntity.ok(converter.userToGetUserDto(prop));
        }

    }

    /**
     * login esta en authenticationController
     */

    @PostMapping("auth/register/gestor")
    public ResponseEntity<GetUserDto>newGestor(@RequestBody CreateUserGestorDto nuevoUser){

        User gest = services.saveGestor(nuevoUser);

        if (gest == null) {
            return ResponseEntity.badRequest().build();
        }
        else {
            return ResponseEntity.ok(converter.userToGetUserDto(gest));
        }

    }
    @PostMapping("auth/register/admin")
    public ResponseEntity<GetUserDto> newAdmin(@RequestBody CreateUserDto nuevoAdmin){

        User admin = services.saveAdmin(nuevoAdmin);

        if (admin == null){
            return ResponseEntity.badRequest().build();
        }
        else {
            return ResponseEntity.ok(converter.userToGetUserDto(admin));
        }

    }
    @GetMapping("/propietarios")
    public ResponseEntity<List<GetUserDto>> getPropietarios(){
        List<GetUserDto> propietarios = new ArrayList<>();
        repo.findByRole(UserRoles.PROPIETARIO).forEach(p->{
            propietarios.add(converter.userToGetUserDto(p));
        });
        return ResponseEntity.ok(propietarios);
    }

    @GetMapping("/propietario/{id}")
    public ResponseEntity<GetUserDto> getPropietarioViviendas(@PathVariable UUID id, @AuthenticationPrincipal User user){
        Optional<User> propietario = repo.findById(id);

        if (propietario.isPresent()) {
            return ResponseEntity.ok(propietario.stream().map(userPropietarioDto.))
        }
    }

}
