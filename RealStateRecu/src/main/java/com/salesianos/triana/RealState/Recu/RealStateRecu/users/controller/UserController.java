package com.salesianos.triana.RealState.Recu.RealStateRecu.users.controller;

import com.salesianos.triana.RealState.Recu.RealStateRecu.security.dto.LoginDto;
import com.salesianos.triana.RealState.Recu.RealStateRecu.services.ViviendaService;
import com.salesianos.triana.RealState.Recu.RealStateRecu.users.dto.*;
import com.salesianos.triana.RealState.Recu.RealStateRecu.users.model.User;
import com.salesianos.triana.RealState.Recu.RealStateRecu.users.model.UserRoles;
import com.salesianos.triana.RealState.Recu.RealStateRecu.users.repo.UserRepository;
import com.salesianos.triana.RealState.Recu.RealStateRecu.users.services.UserServices;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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

    @Autowired
    private UserServices services;
    @Autowired
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
        repo.findByRoles(UserRoles.PROPIETARIO).forEach(p->{
            propietarios.add(converter.userToGetUserDto(p));
        });
        return ResponseEntity.ok(propietarios);
    }

    /**
     * Solo se podrá eliminar el usuario propietario en si o un ADMIN
     * @param id
     * @param user
     * @return
     */

    public ResponseEntity<?> deletePropietario(@PathVariable UUID id, @AuthenticationPrincipal User user){

        Optional<User> userABuscar = repo.findById(id);

        if (userABuscar.isPresent()){
            if (user.getId().equals(userABuscar.get().getId()) || user.getRoles().equals(UserRoles.ADMIN)){
                repo.deleteById(id);
                return ResponseEntity.noContent().build();
            } else{
                return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
            }
        }

        return ResponseEntity.noContent().build();

    }

}
