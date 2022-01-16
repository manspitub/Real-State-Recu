package com.salesianos.triana.RealState.Recu.RealStateRecu.repos;

import com.salesianos.triana.RealState.Recu.RealStateRecu.model.Inmobiliaria;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface InmobiliariaRepository extends JpaRepository<Inmobiliaria, Long> {

    Optional<Inmobiliaria> findFirstByName(String name);


    List<Inmobiliaria> findByIdNotNull();

}
