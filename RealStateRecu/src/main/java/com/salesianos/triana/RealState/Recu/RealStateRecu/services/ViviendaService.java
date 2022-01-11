package com.salesianos.triana.RealState.Recu.RealStateRecu.services;

import com.salesianos.triana.RealState.Recu.RealStateRecu.dto.ViviendaDtos.GetViviendaDto;
import com.salesianos.triana.RealState.Recu.RealStateRecu.dto.ViviendaDtos.ViviendaDtoConverter;
import com.salesianos.triana.RealState.Recu.RealStateRecu.model.Vivienda;
import com.salesianos.triana.RealState.Recu.RealStateRecu.repos.ViviendaRepository;
import com.salesianos.triana.RealState.Recu.RealStateRecu.services.base.BaseService;
import com.salesianos.triana.RealState.Recu.RealStateRecu.users.model.User;
import com.salesianos.triana.RealState.Recu.RealStateRecu.users.repo.UserRepository;
import com.salesianos.triana.RealState.Recu.RealStateRecu.users.services.UserServices;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service("viviendaService")
@RequiredArgsConstructor
public class ViviendaService extends BaseService<Vivienda, Long, ViviendaRepository> {

    @Autowired
    private final UserRepository userRepo;

    @Autowired
    private final ViviendaDtoConverter dtoConverter;

    public List<GetViviendaDto> getViviendaDtosProp(User u){
        List<GetViviendaDto> getViviendas = new ArrayList<>();
        Optional<User> prop = userRepo.findById(u.getId());
        if (prop.isPresent()) {
            List<Vivienda> viviendasPropietario = prop.get().getViviendas();
            viviendasPropietario.forEach(vivienda -> {
                getViviendas.add(dtoConverter.viviendaToGetViviendaDto(vivienda));
            });
        }
        return getViviendas;
    }

}
