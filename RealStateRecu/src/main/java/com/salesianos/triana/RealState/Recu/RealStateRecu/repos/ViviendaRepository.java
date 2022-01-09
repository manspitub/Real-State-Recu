package com.salesianos.triana.RealState.Recu.RealStateRecu.repos;

import com.salesianos.triana.RealState.Recu.RealStateRecu.model.Vivienda;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ViviendaRepository extends JpaRepository<Vivienda, Long> {

    Optional<Vivienda> findFirstByDireccion(String direccion);
}
