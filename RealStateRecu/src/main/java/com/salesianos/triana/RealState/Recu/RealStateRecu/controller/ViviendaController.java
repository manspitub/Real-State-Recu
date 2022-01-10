package com.salesianos.triana.RealState.Recu.RealStateRecu.controller;

import com.salesianos.triana.RealState.Recu.RealStateRecu.PaginationLinksUtils;
import com.salesianos.triana.RealState.Recu.RealStateRecu.dto.ViviendaDtos.CreateViviendaDto;
import com.salesianos.triana.RealState.Recu.RealStateRecu.dto.ViviendaDtos.GetViviendaDto;
import com.salesianos.triana.RealState.Recu.RealStateRecu.dto.ViviendaDtos.GetViviendaPropietario;
import com.salesianos.triana.RealState.Recu.RealStateRecu.dto.ViviendaDtos.ViviendaDtoConverter;
import com.salesianos.triana.RealState.Recu.RealStateRecu.model.Inmobiliaria;
import com.salesianos.triana.RealState.Recu.RealStateRecu.model.Vivienda;
import com.salesianos.triana.RealState.Recu.RealStateRecu.repos.InmobiliariaRepository;
import com.salesianos.triana.RealState.Recu.RealStateRecu.repos.ViviendaRepository;
import com.salesianos.triana.RealState.Recu.RealStateRecu.users.model.User;
import com.salesianos.triana.RealState.Recu.RealStateRecu.users.model.UserRoles;
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
import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
public class ViviendaController {

    private final ViviendaDtoConverter converter;
    private final ViviendaRepository repo;
    private final InmobiliariaRepository inmoRepo;
    private final PaginationLinksUtils paginationLinksUtils;



    /**
     * Solo puede registrar viviendas los usuarios propietarios
     * @param viviendaDto
     * @param prop
     * @return
     */
    @PostMapping("vivienda/")
    public ResponseEntity<GetViviendaDto> newVivienda(@RequestBody CreateViviendaDto viviendaDto, @AuthenticationPrincipal User prop){
        if (prop.getRoles().equals(UserRoles.PROPIETARIO)){

            Vivienda viv = converter.createViviendaDtoToVivienda(viviendaDto);
            /**
             * propietario de la vivienda
             */
            viv.addPropietario(prop);
            repo.save(viv);
            return ResponseEntity.status(HttpStatus.CREATED).body(converter.viviendaToGetViviendaDto(viv));


        } else {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
    }

    /**
     * Ejemplo con pageable
     * @return
     */

    @GetMapping("vivienda")
    public ResponseEntity<?> allViviendas(@PageableDefault (size = 10, page = 0)Pageable pageable, HttpServletRequest request){
        Page<Vivienda> result = repo.findAll(pageable);

        if (result.isEmpty()){
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } else {
            Page<GetViviendaDto> dto = result.map(converter::viviendaToGetViviendaDto);
            UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromHttpUrl(request.getRequestURL().toString());

            return ResponseEntity.ok().header("link", paginationLinksUtils.createLinkHeader(dto, uriBuilder)).body(dto);
        }
    }

    /**
     * Datos de una vivienda, incluido del propietario
     */

    public ResponseEntity<GetViviendaPropietario> getViviendaProp(@PathVariable Long id){

        Optional<Vivienda> vivienda = repo.findById(id);

        return vivienda.map(value -> ResponseEntity.ok(converter.viviendaToGetViviendaPropietarioDto(value, value.getPropietario()))).orElseGet(() -> ResponseEntity.notFound().build());

    }

    /**
     * Editar una vivienda con todos sus datos
     * @param id
     * @param viviendaAEditar
     * @param user
     * @return
     */

    @PutMapping("vivienda/{id}")
    public ResponseEntity<GetViviendaDto> editVivienda(@PathVariable Long id, @RequestBody CreateViviendaDto viviendaAEditar, @AuthenticationPrincipal User user){

        Optional<Vivienda> viviendaEdit = repo.findById(id);

        if (user.getRoles().equals(UserRoles.ADMIN) || viviendaEdit.get().getPropietario().getId().equals(user.getId())) {
            if (viviendaEdit.isPresent()){

                viviendaEdit.map(v -> {
                    v.setTitulo(viviendaAEditar.getTitulo());
                    v.setDescripcion(viviendaAEditar.getDescripcion());
                    v.setAvatar(viviendaAEditar.getAvatar());
                    v.setLating(viviendaAEditar.getLating());
                    v.setDireccion(viviendaAEditar.getDireccion());
                    v.setCodigoPostal(viviendaAEditar.getCodigoPostal());
                    v.setPoblacion(viviendaAEditar.getPoblacion());
                    v.setProvincia(viviendaAEditar.getProvincia());
                    v.setTipo(viviendaAEditar.getTipo());
                    v.setPrecio(viviendaAEditar.getPrecio());
                    v.setNumHabitaciones(viviendaAEditar.getNumHabitaciones());
                    v.setNumBanios(viviendaAEditar.getNumBanios());
                    v.setMetrosCuadrados(viviendaAEditar.getMetrosCuadrados());
                    v.setTieneAscensor(viviendaAEditar.isTieneAscensor());
                    v.setTienePiscina(viviendaAEditar.isTienePiscina());
                    v.setTieneGaraje(viviendaAEditar.isTieneGaraje());
                    return v;
                });
                repo.save(viviendaEdit.get());

                return ResponseEntity.ok(converter.viviendaToGetViviendaDto(viviendaEdit.get()));
            } else {
                return ResponseEntity.notFound().build();
            }
        }
        else{
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }


    }

    /**
     * A su vez se deben eliminar sus intereses
     * @return
     */

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id, @AuthenticationPrincipal User user){
        Optional<Vivienda> viviendaABorrar = repo.findById(id);
        if (viviendaABorrar.isPresent()) {
            if (user.getRoles().equals(UserRoles.ADMIN) || viviendaABorrar.get().getPropietario().getId().equals(user.getId())){
                viviendaABorrar.get().removeInmobiliaria();
                viviendaABorrar.get().removePropietario();
                viviendaABorrar.get().removeInteresas();
                repo.delete(viviendaABorrar.get());
            }
        }
        return ResponseEntity.noContent().build();
    }

    @PostMapping("vivenda/{idVivienda}/inmobiliaria/{idInmo}")
    public ResponseEntity<GetViviendaDto> viviendaInmo(@PathVariable Long idVivienda, @PathVariable Long idInmo, @AuthenticationPrincipal User user ){
        Optional<Vivienda> vivienda = repo.findById(idVivienda);
        Optional<Inmobiliaria> inmobiliaria = inmoRepo.findById(idInmo);
        if (inmobiliaria.isPresent() && vivienda.isPresent()){
            if (user.getRoles().equals(UserRoles.ADMIN) || vivienda.get().getPropietario().getId().equals(user.getId())){
                vivienda.get().addInmobiliaria(inmobiliaria.get());
                repo.save(vivienda.get());
                return ResponseEntity.ok(converter.viviendaToGetViviendaDto(vivienda.get()));
            } else {
                ResponseEntity.status(HttpStatus.FORBIDDEN).build();
            }
        }
        else {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(converter.viviendaToGetViviendaDto(vivienda.get()));

    }

    public ResponseEntity<?> deleteViviendaInmo(@PathVariable Long idVivienda, @AuthenticationPrincipal User user){
        Optional<Vivienda> vivienda = repo.findById(idVivienda);

        if (vivienda.isPresent()) {
            if (vivienda.get().getPropietario().getId().equals(user.getId())){
                vivienda.get().removeInmobiliaria();
                repo.save(vivienda.get());
                return ResponseEntity.noContent().build();
            }
        }
        return ResponseEntity.noContent().build();
    }





}
