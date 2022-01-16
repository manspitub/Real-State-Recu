package com.salesianos.triana.RealState.Recu.RealStateRecu.users.repo;

import com.salesianos.triana.RealState.Recu.RealStateRecu.users.model.User;
import com.salesianos.triana.RealState.Recu.RealStateRecu.users.model.UserRoles;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<User, UUID> {

    Optional<User> findFirstByEmail(String email);

    List<User> findByNameContainsIgnoreCase(String name);

    List<User> findByRoles (UserRoles role);

    List<User> findByAdress (String adress);

    Optional<User> findById(UUID id);

}
