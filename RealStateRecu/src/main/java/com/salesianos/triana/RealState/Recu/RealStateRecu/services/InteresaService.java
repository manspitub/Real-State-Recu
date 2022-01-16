package com.salesianos.triana.RealState.Recu.RealStateRecu.services;

import com.salesianos.triana.RealState.Recu.RealStateRecu.dto.ViviendaDtos.GetViviendaDto;
import com.salesianos.triana.RealState.Recu.RealStateRecu.dto.ViviendaDtos.ViviendaDtoConverter;
import com.salesianos.triana.RealState.Recu.RealStateRecu.model.Interesa;
import com.salesianos.triana.RealState.Recu.RealStateRecu.model.Vivienda;
import com.salesianos.triana.RealState.Recu.RealStateRecu.repos.InteresaRepository;
import com.salesianos.triana.RealState.Recu.RealStateRecu.services.base.BaseService;
import com.salesianos.triana.RealState.Recu.RealStateRecu.users.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service("interesaService")
@RequiredArgsConstructor
public class InteresaService extends BaseService<Interesa, Long, InteresaRepository> {


    private final ViviendaDtoConverter dtoConverter;

    private final InteresaRepository repository;

    public List<User> getInteresados(){
        return repository.findInteresados();
    }



    public List<GetViviendaDto> topViviendaDto(){
        List<GetViviendaDto> viviendas = new ArrayList<>();

        repository.top10ViviendasInteresas().forEach(v ->{
            viviendas.add(dtoConverter.viviendaToGetViviendaDto(v));
        });
        return viviendas;
    }

}
