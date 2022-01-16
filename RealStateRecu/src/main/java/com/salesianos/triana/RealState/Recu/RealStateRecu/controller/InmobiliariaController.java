package com.salesianos.triana.RealState.Recu.RealStateRecu.controller;


import com.salesianos.triana.RealState.Recu.RealStateRecu.PaginationLinksUtils;
import com.salesianos.triana.RealState.Recu.RealStateRecu.dto.InmobiliariaDtos.CreateGestorInmoDto;
import com.salesianos.triana.RealState.Recu.RealStateRecu.dto.InmobiliariaDtos.CreateInmobiliariaDto;
import com.salesianos.triana.RealState.Recu.RealStateRecu.dto.InmobiliariaDtos.GetInmobiliariaDto;
import com.salesianos.triana.RealState.Recu.RealStateRecu.dto.InmobiliariaDtos.InmobiliariaDtoConverter;
import com.salesianos.triana.RealState.Recu.RealStateRecu.model.Inmobiliaria;
import com.salesianos.triana.RealState.Recu.RealStateRecu.repos.InmobiliariaRepository;
import com.salesianos.triana.RealState.Recu.RealStateRecu.services.InmoService;
import com.salesianos.triana.RealState.Recu.RealStateRecu.users.dto.GetUserDto;
import com.salesianos.triana.RealState.Recu.RealStateRecu.users.dto.GetUserGestorDto;
import com.salesianos.triana.RealState.Recu.RealStateRecu.users.dto.UserDtoConverter;
import com.salesianos.triana.RealState.Recu.RealStateRecu.users.model.User;
import com.salesianos.triana.RealState.Recu.RealStateRecu.users.model.UserRoles;
import com.salesianos.triana.RealState.Recu.RealStateRecu.users.repo.UserRepository;
import com.salesianos.triana.RealState.Recu.RealStateRecu.users.services.UserServices;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.net.http.HttpResponse;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@CrossOrigin
@RequiredArgsConstructor

public class InmobiliariaController {

    private final InmobiliariaRepository repo;
    private final InmobiliariaDtoConverter inmoConverter;
    private final UserDtoConverter userDtoConverter;
    private final InmoService service;
    private final UserRepository userRepository;
    private final UserServices uServices;
    private final PaginationLinksUtils paginationLinksUtils;



    @ApiOperation(value = "Get", notes = "Devuelve una lista con todas las inmos")
    @ApiResponses({
            @ApiResponse(code = HttpServletResponse.SC_OK, message = "")
    })
    @GetMapping("/inmobiliaria")
    public ResponseEntity<Page<GetInmobiliariaDto>> findAllInmos(@PageableDefault(size = 10, page = 0)Pageable pageable, HttpServletRequest request)  {
        Page<Inmobiliaria> data= service.findAll(pageable);
        if (data.isEmpty()){
            return ResponseEntity.notFound().build();
        }else {
            Page<GetInmobiliariaDto> result=
                    data.map(inmoConverter::inmobiliariaToGetInmobiliariaDto);

            UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromHttpUrl(request.getRequestURL().toString());

            return ResponseEntity.ok().header("Link" ,
                    paginationLinksUtils.createLinkHeader(result , uriBuilder)).body(result);
        }
    }

    @PostMapping("/inmobiliaria")
    public ResponseEntity<?> newInmo(@RequestBody CreateInmobiliariaDto inmo, @AuthenticationPrincipal User user){
        if (user.getRoles().equals(UserRoles.ADMIN)){
            Inmobiliaria inmobiliaria =inmoConverter.createInmobiliariaDtoToInmobiliaria(inmo);
            repo.save(inmobiliaria);
            return ResponseEntity.ok(inmoConverter.inmobiliariaToGetInmobiliariaDto(inmobiliaria));
        }
        else {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
    }

    @PostMapping("/inmobiliaria/{id}/gestor")
    public ResponseEntity<GetUserDto> newGestorInmo(@PathVariable Long id, @RequestBody CreateGestorInmoDto newGestor, @AuthenticationPrincipal User user){
        Optional<Inmobiliaria> inmoABuscar = service.findById(id);

        if (inmoABuscar.isPresent()){
            if (user.getRoles().equals(UserRoles.ADMIN) || service.checkGestorBelongsToInmo(user, inmoABuscar.get())){
                User newUser = inmoConverter.createGestorInmoDtoToGestor(newGestor, inmoABuscar.get());
                userRepository.save(newUser);
                return ResponseEntity.status(HttpStatus.CREATED).body(userDtoConverter.userToGetUserDto(newUser));
            }else {
                return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
            }
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @DeleteMapping("/gestor/{id}")
    public ResponseEntity<?> removeGestor(@PathVariable UUID id, @AuthenticationPrincipal User userLogged){

        Optional<User> gestor = userRepository.findById(id);
        Inmobiliaria inmo = userLogged.getInmobiliaria();

        if(gestor.isPresent()){
            if(userLogged.getRoles().equals(UserRoles.ADMIN) || service.checkGestorBelongsToInmo(gestor.get(),inmo)){
                userRepository.deleteById(id);
            }
        }

        return ResponseEntity.noContent().build();

    }

    @GetMapping("{id}/gestor")
    public ResponseEntity<List<GetUserDto>> allGestoresInmo(@PathVariable Long id, @AuthenticationPrincipal User user){
        Optional<Inmobiliaria> inmobiliariaAbuscar = repo.findById(id);

        if (inmobiliariaAbuscar.isPresent()){
            if (service.checkGestorBelongsToInmo(user, inmobiliariaAbuscar.get()) || user.getRoles().equals(UserRoles.ADMIN)){
                List<GetUserDto> gestores = uServices.userToGetUserDto(inmobiliariaAbuscar.get().getGestores());

                return ResponseEntity.ok(gestores);
            }
            else{
                return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
            }
        }
        else {
            return ResponseEntity.notFound().build();
        }
    }



}
